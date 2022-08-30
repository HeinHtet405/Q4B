package com.onesmartstar.heinhtetaung.q4b.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onesmartstar.heinhtetaung.q4b.ui.theme.AccentColor

@Composable
fun GradientButton(
    modifier: Modifier,
    text: String,
    textColor: Color,
    enabled: Boolean,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = AccentColor),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 10.dp, pressedElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(backgroundColor)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}