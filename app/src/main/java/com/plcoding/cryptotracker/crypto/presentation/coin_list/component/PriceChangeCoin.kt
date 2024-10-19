package com.plcoding.cryptotracker.crypto.presentation.coin_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.presentation.model.DisplayNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChangeCoin(
    displayNumber: DisplayNumber,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (displayNumber.price < 0.0) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    val contentColor = if (displayNumber.price < 0.0) {
        Color.Red
    } else {
        Color.Green
    }

    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(100f))
            .background(color = backgroundColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = if (displayNumber.price < 0.0) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = contentColor
        )
        Text(
            text = "${displayNumber.formatter} %",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangeCoinPreview() {
    CryptoTrackerTheme {
        PriceChangeCoin(
            displayNumber = DisplayNumber(
                price = 2.4,
                formatter = "2,4"
            )
        )
    }
}