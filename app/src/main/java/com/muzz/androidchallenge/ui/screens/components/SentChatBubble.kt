package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun SentChatBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MuzzSpacing.spacing16,
                vertical = MuzzSpacing.spacing4
            ),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MuzzColor.LightGrey,
                    shape = RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = 0f,
                        bottomEnd = 48f
                    )
                )
                .padding(MuzzSpacing.spacing12)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                style = MuzzTypography.bodyLarge,
                color = MuzzColor.DarkGrey
            )
        }
    }
}
