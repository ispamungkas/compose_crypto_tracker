package com.plcoding.cryptotracker.crypto.presentation.coin_detail.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.core.presentation.utils.getDrawableIdForCoin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    title: String,
    formatterText: String,
    icon: ImageVector,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier,
) {
    val defaultFormatStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        color = contentColor
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        ),
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
        ),
    ) {
        AnimatedContent(
            targetState = icon,
            label = "Icon $title",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = it,
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(75.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = formatterText,
            label = "Title $title",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(horizontal = 16.dp)
        ) {
            Text(text = "$ ${it}", style = defaultFormatStyle)
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = title,
            label = "Title $title",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(horizontal = 16.dp)
        ) {
            Text(
                text = it, style = defaultFormatStyle.copy(
                    fontSize = 14.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    CryptoTrackerTheme {
        InfoCard(
            title = "Bitcoin",
            formatterText = "BTC",
            icon = ImageVector.vectorResource(id = getDrawableIdForCoin("BTC")),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}