package com.onesmartstar.heinhtetaung.q4b.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GreyTextColor
import com.onesmartstar.heinhtetaung.q4b.R

@Composable
fun EmptyView(
    message: String,
    imgDrawable: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = imgDrawable),
            contentDescription = message,
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp),
            text = message,
            color = GreyTextColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}