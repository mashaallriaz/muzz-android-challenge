package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.muzz.androidchallenge.R
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun ChatInputBar(onSendMessage: (String) -> Unit) {
    var chatInputValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MuzzColor.White)
    ) {
        HorizontalDivider(
            modifier = Modifier.shadow(elevation = 1.dp),
            thickness = 1.dp,
            color = MuzzColor.Transparent
        )

        Spacer(Modifier.height(MuzzSpacing.spacing12))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(
                    horizontal = MuzzSpacing.spacing16,
                    vertical = MuzzSpacing.spacing12
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = chatInputValue,
                onValueChange = { chatInputValue = it },
                shape = RoundedCornerShape(28.dp),
                textStyle = MuzzTypography.bodyLarge,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(max = 120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MuzzColor.DarkGrey,
                    focusedContainerColor = MuzzColor.White,
                    focusedBorderColor = MuzzColor.PrimaryPink,
                    unfocusedBorderColor = MuzzColor.PrimaryPink
                )
            )

            Spacer(Modifier.width(MuzzSpacing.spacing12))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (chatInputValue.text.isNotBlank()) MuzzColor.PrimaryPink else MuzzColor.SecondaryPink)
                    .padding(MuzzSpacing.spacing12)
                    .clickable(enabled = chatInputValue.text.isNotBlank()) {
                        onSendMessage(chatInputValue.text)
                        chatInputValue = TextFieldValue("")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}