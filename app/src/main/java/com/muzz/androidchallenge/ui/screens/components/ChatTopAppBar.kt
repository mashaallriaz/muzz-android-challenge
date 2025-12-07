package com.muzz.androidchallenge.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.muzz.androidchallenge.R
import com.muzz.androidchallenge.domain.models.User
import com.muzz.androidchallenge.ui.theme.MuzzColor
import com.muzz.androidchallenge.ui.theme.MuzzSpacing
import com.muzz.androidchallenge.ui.theme.MuzzTypography

@Composable
fun ChatTopAppBar(currentUser: User?, onSwitchUser: (User) -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

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

            Icon(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                tint = if (currentUser == User.SARAH) MuzzColor.White else MuzzColor.DarkGrey,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (currentUser == User.SARAH) MuzzColor.PrimaryPink else MuzzColor.LightGrey)
                    .padding(MuzzSpacing.spacing12)
            )

            Spacer(Modifier.width(MuzzSpacing.spacing8))

            Text(
                text = currentUser?.displayName ?: "",
                style = MuzzTypography.headlineLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_more_horizontal),
                        contentDescription = null,
                        tint = MuzzColor.MediumGrey,
                        modifier = Modifier.size(32.dp)
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    containerColor = MuzzColor.ExtraLightGrey,
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 1.dp,
                    border = BorderStroke(1.dp, MuzzColor.LightGrey)
                ) {
                    val targetUser = if (currentUser == User.SARAH) User.OTHER else User.SARAH
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Switch to ${targetUser.displayName}",
                                style = MuzzTypography.bodyLarge,
                                color = MuzzColor.DarkGrey
                            )
                        },
                        onClick = {
                            onSwitchUser(targetUser)
                            menuExpanded = false
                        }
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.shadow(elevation = 1.dp),
            thickness = 1.dp,
            color = MuzzColor.Transparent
        )
    }
}