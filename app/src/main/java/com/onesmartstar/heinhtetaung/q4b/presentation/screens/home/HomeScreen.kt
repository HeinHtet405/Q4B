package com.onesmartstar.heinhtetaung.q4b.presentation.screens.home

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.onesmartstar.heinhtetaung.q4b.R
import com.onesmartstar.heinhtetaung.q4b.data.ApiConstant.FILE_URL
import com.onesmartstar.heinhtetaung.q4b.domain.model.Item
import com.onesmartstar.heinhtetaung.q4b.domain.model.local.FileData
import com.onesmartstar.heinhtetaung.q4b.presentation.common.EmptyView
import com.onesmartstar.heinhtetaung.q4b.presentation.common.GradientButton
import com.onesmartstar.heinhtetaung.q4b.presentation.common.TopAppBarBack
import com.onesmartstar.heinhtetaung.q4b.ui.theme.AccentColor
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GreyMedium
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GreyTextColor
import com.onesmartstar.heinhtetaung.q4b.util.FileDownloadWorker
import java.io.File

@ExperimentalPermissionsApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    permissions: List<String>,
    homeViewModel: HomeViewModel = hiltViewModel(),
    activity: ComponentActivity
) {
    val context = LocalContext.current
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    val state by homeViewModel.state.collectAsState()
    val progress = remember { mutableStateOf(0) }
    var btnEnable = true

    Scaffold(
        topBar = {
            TopAppBarBack(
                title = "Highlights & Guidelines",
                navigateScreen = {
                    // do nothing
                },
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Plans",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                if (multiplePermissionsState.allPermissionsGranted) {
                    if (state.isEmpty()) {
                        //  Show Empty View
                        EmptyView("No plan, yet", R.drawable.img_empty_plan)

                    } else {

                        val downloadList = mutableListOf<FileData>()
                        state.forEach { item ->
                            val strList = item.fileOriginalName.split(".").toTypedArray()
                            if (strList.size > 1) {
                                var fileType: String
                                fileType = strList[1].uppercase()
                                if (fileType == "JPG") {
                                    fileType = "PNG"
                                }
                                if (item.fileOriginalName.isNotEmpty() && item.fileOriginalName != "/") {
                                    val fileUrl = FILE_URL + item.fileOriginalName
                                    val fileData = FileData(
                                        id = item.id,
                                        name = item.name,
                                        type = fileType,
                                        originalUrl = item.fileOriginalName,
                                        url = fileUrl
                                    )
                                    downloadList += fileData
                                }
                            }
                        }

                        if (checkFiles() == 0) {

                            LaunchedEffect(key1 = downloadList.size) {
                                downloadList.forEach { fileData ->
                                    startDownloadingFile(
                                        context = context,
                                        lifeCycleOwner = activity,
                                        file = fileData,
                                        success = {
                                            val updateItem = Item(
                                                id = fileData.id,
                                                name = fileData.name,
                                                fileOriginalName = fileData.originalUrl,
                                                type = fileData.type,
                                                downloadedUri = it
                                            )
                                            homeViewModel.updateItem(updateItem)
                                            progress.value += 1
                                        },
                                        failed = {
                                            Log.i("HomeScreen", "Failed...")
                                        },
                                        running = {
                                            Log.i("HomeScreen", "Downloading...")
                                        }
                                    )
                                }
                            }
                        }

                        val isVisible = downloadList.size != checkFiles()
                        if (isVisible) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Downloading Plans...",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = GreyTextColor,
                                )
                                Text(
                                    text = "${progress.value}/${downloadList.size}",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = GreyTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            btnEnable = true
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                            ) {
                                items(state) { plan ->
                                    ItemCard(
                                        item = plan,
                                        onClickAction = { item ->
                                            if (item.fileOriginalName == "/") {
                                                Toast.makeText(
                                                    context,
                                                    "Empty File.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                val intent = Intent(Intent.ACTION_VIEW)
                                                if (item.type == "PDF") {
                                                    intent.setDataAndType(
                                                        item.downloadedUri?.toUri(),
                                                        "application/pdf"
                                                    )
                                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                                } else {
                                                    val uri =
                                                        Uri.parse(FILE_URL + item.fileOriginalName)
                                                    intent.setDataAndType(uri, "image/*")
                                                }
                                                try {
                                                    startActivity(context, intent, null)
                                                } catch (e: ActivityNotFoundException) {
                                                    Toast.makeText(
                                                        context,
                                                        "Can't open Pdf",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(80.dp))
                                }
                            }
                        }
                    }
                } else {
                    EmptyView(
                        message = "Please give permissions.",
                        imgDrawable = R.drawable.img_request
                    )
                }

            }
            GradientButton(
                Modifier
                    .width(260.dp)
                    .height(50.dp)
                    .align(Alignment.BottomCenter),
                text = "Download",
                textColor = Color.White,
                enabled = btnEnable,
                backgroundColor = if (btnEnable) AccentColor else GreyMedium,
                onClick = {
                    if (!multiplePermissionsState.allPermissionsGranted) {
                        btnEnable = true
                        multiplePermissionsState.launchMultiplePermissionRequest()
                    } else {
                        btnEnable = false
                        if (checkFiles() != 0) {
                            deleteAllFiles()
                        }
                        homeViewModel.getItemList()
                    }
                }
            )
        }
    }
}

private fun startDownloadingFile(
    context: Context,
    lifeCycleOwner: LifecycleOwner,
    file: FileData,
    success: (String) -> Unit,
    failed: (String) -> Unit,
    running: () -> Unit
) {
    val groupName = "plan_file_download_group"
    val data = Data.Builder()

    data.apply {
        putString(FileDownloadWorker.FileParams.KEY_FILE_NAME, file.name)
        putString(FileDownloadWorker.FileParams.KEY_FILE_URL, file.url)
        putString(FileDownloadWorker.FileParams.KEY_FILE_TYPE, file.type)
    }

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresStorageNotLow(true)
        .setRequiresBatteryNotLow(true)
        .build()

    val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownloadWorker>()
        .setConstraints(constraints)
        .setInputData(data.build())
        .build()

    val workManager: WorkManager = WorkManager.getInstance(context)

    workManager.beginUniqueWork(
        groupName,
        ExistingWorkPolicy.APPEND,
        fileDownloadWorker
    ).enqueue()

    workManager.getWorkInfoByIdLiveData(fileDownloadWorker.id)
        .observe(lifeCycleOwner) { info ->
            info?.let {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        success(
                            it.outputData.getString(FileDownloadWorker.FileParams.KEY_FILE_URI)
                                ?: ""
                        )
                    }
                    WorkInfo.State.FAILED -> {
                        failed("Downloading failed!")
                    }
                    WorkInfo.State.RUNNING -> {
                        running()
                    }
                    else -> {
                        failed("Something went wrong")
                    }
                }
            }
        }
}

private fun deleteAllFiles() {
    val dir = File(Environment.getExternalStorageDirectory().toString() + "/Download/Q4BPlanData")
    if (dir.isDirectory) {
        val children: Array<String> = dir.list() as Array<String>
        if (children.isNotEmpty()) {
            for (i in children.indices) {
                File(dir, children[i]).delete()
            }
        }
    }
}

private fun checkFiles(): Int {
    val dir = File(Environment.getExternalStorageDirectory().toString() + "/Download/Q4BPlanData")
    if (dir.isDirectory) {
        val children: Array<String> = dir.list() as Array<String>
        return children.size
    }
    return 0
}
