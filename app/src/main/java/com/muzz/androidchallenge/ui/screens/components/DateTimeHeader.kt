package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun DateTimeHeader(timestampText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MuzzSpacing.spacing8),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timestampText,
            style = MuzzTypography.bodyMedium,
            color = MuzzColor.MediumGrey
        )
    }
}