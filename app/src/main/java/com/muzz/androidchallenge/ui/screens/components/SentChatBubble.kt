package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.muzz.androidchallenge.R
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun SentChatBubble(message: Message, isGrouped: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MuzzSpacing.spacing16,
                end = MuzzSpacing.spacing16,
                top = if (isGrouped) MuzzSpacing.spacing2 else MuzzSpacing.spacing12
            ),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MuzzColor.PrimaryPink,
                    shape = RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = 48f,
                        bottomEnd = 0f
                    )
                )
                .padding(MuzzSpacing.spacing12)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                style = MuzzTypography.bodyLarge,
                color = MuzzColor.White,
                modifier = Modifier.padding(end = MuzzSpacing.spacing24)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_double_tick),
                tint = MuzzColor.White,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}
