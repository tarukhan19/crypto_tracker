package com.demo.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.demo.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChange(
    change: DisplayableNumber?,
    modifier: Modifier = Modifier
) {
    if (change == null) return // Exit early if change is null to avoid crashes

    val isNegative = change.value < 0.0

    val contentColor = if (isNegative) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }

    val backgroundColor = if (isNegative) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    val icon = if (isNegative) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.Default.KeyboardArrowUp
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )
        Text(
            text = "${change.formatted} %",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = contentColor
        )
    }
}