package com.muzz.androidchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.muzz.androidchallenge.R

val brinkCobaneFontFamily = FontFamily(
    Font(R.font.brink_cobane_regular, FontWeight.Normal),
    Font(R.font.brink_cobane_medium, FontWeight.Medium),
    Font(R.font.brink_cobane_bold, FontWeight.Bold)
)

val MuzzTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = brinkCobaneFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = brinkCobaneFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = brinkCobaneFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = brinkCobaneFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontFamily = brinkCobaneFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)