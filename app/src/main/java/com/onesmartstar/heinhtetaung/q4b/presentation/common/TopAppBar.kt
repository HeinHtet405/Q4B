package com.onesmartstar.heinhtetaung.q4b.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.onesmartstar.heinhtetaung.q4b.ui.theme.PrimaryDarkColor

@Composable
fun TopAppBarBack(
    title: String,
    navigateScreen: () -> Unit,
) {
    androidx.compose.material.TopAppBar(
        modifier = Modifier.background(
            PrimaryDarkColor
        ),
        navigationIcon = {
            BackAction(onBackClicked = navigateScreen)
        },
        title = {
            Text(
                modifier = Modifier.padding(4.dp),
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    )
}

@Composable
fun BackAction(
    onBackClicked: () -> Unit
) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Arrow Icon",
            tint = Color.White
        )
    }
}