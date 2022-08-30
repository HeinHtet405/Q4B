package com.onesmartstar.heinhtetaung.q4b.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.onesmartstar.heinhtetaung.q4b.domain.model.Item
import com.onesmartstar.heinhtetaung.q4b.ui.theme.AliceBlue
import com.onesmartstar.heinhtetaung.q4b.ui.theme.GreyTextColor
import com.onesmartstar.heinhtetaung.q4b.ui.theme.PaleBlue

@Composable
fun ItemCard(
    item: Item,
    onClickAction: (Item) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 16.dp)
            .clickable {
                onClickAction(item)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        backgroundColor = AliceBlue,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 4.dp),
                text = "Plan_name: ",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f).padding(2.dp),
                text = item.name,
                style = MaterialTheme.typography.subtitle1,
                color = GreyTextColor,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(6.dp).fillMaxHeight().background(PaleBlue))
        }
    }
}