package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.muzz.androidchallenge.R
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun ChatTopAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MuzzColor.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MuzzSpacing.spacing12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = MuzzColor.PrimaryPink,
                modifier = Modifier.size(32.dp)
            )

            Spacer(Modifier.width(MuzzSpacing.spacing12))

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MuzzColor.PrimaryPink)
            )

            Spacer(Modifier.width(MuzzSpacing.spacing8))

            Text(
                text = "Sarah",
                style = MuzzTypography.headlineLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_more_horizontal),
                contentDescription = null,
                tint = MuzzColor.MediumGrey,
                modifier = Modifier.size(32.dp)
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier.shadow(elevation = 1.dp),
        thickness = 1.dp,
        color = MuzzColor.Transparent
    )
}