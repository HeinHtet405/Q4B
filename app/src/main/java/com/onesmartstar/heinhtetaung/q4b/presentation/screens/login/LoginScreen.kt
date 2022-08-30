package com.onesmartstar.heinhtetaung.q4b.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.onesmartstar.heinhtetaung.q4b.R
import com.onesmartstar.heinhtetaung.q4b.navigation.Screen
import com.onesmartstar.heinhtetaung.q4b.presentation.common.CustomInputField
import com.onesmartstar.heinhtetaung.q4b.presentation.common.GradientButton
import com.onesmartstar.heinhtetaung.q4b.ui.theme.AccentColor
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GradientColorEnd
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GradientColorStart
import com.onesmartstar.heinhtetaung.q4b.util.Constants.ERROR_RESPONSE
import com.onesmartstar.heinhtetaung.q4b.util.Constants.SUCCESS_RESPONSE

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    val inputValueID = remember {
        mutableStateOf("")
    }

    val inputValuePass = remember {
        mutableStateOf("")
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }

    val errorStatus by loginViewModel.errorMessage.collectAsState()
    if (errorStatus == ERROR_RESPONSE) {
        LaunchedEffect(key1 = errorStatus) {
            showDialog = false
            Toast.makeText(
                context,
                "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    } else if (errorStatus == SUCCESS_RESPONSE) {
        LaunchedEffect(key1 = errorStatus) {
            showDialog = false
            Toast.makeText(
                context,
                "Login Success.",
                Toast.LENGTH_SHORT
            ).show()
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(listOf(GradientColorStart, GradientColorEnd)),
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = "Logo Image"
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(20.dp),
                text = "Welcome to Q4B",
                color = White,
                style = MaterialTheme.typography.h6
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            CustomInputField(inputValue = inputValueID, type = "uid")
            Spacer(modifier = Modifier.height(8.dp))
            CustomInputField(inputValue = inputValuePass, type = "password")
            Spacer(modifier = Modifier.height(24.dp))
            GradientButton(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                text = "Login",
                textColor = White,
                enabled = true,
                backgroundColor = AccentColor,
                onClick = {
                    val checkField = loginViewModel.validationFields(
                        valueId = inputValueID.value,
                        password = inputValuePass.value
                    )
                    if (checkField) {
                        showDialog = true
                        loginViewModel.setLogin(
                            valueId = inputValueID.value,
                            password = inputValuePass.value,
                        )
                    } else {
                        Toast.makeText(context, "Please require fields.", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}