package com.muzz.androidchallenge.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeFormatter {
    @SuppressLint("ConstantLocale")
    private val formatter = SimpleDateFormat("EEEE HH:mm", Locale.getDefault())

    fun format(timestamp: Long): String {
        return formatter.format(Date(timestamp))
    }
}