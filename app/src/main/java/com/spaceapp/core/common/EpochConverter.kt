package com.spaceapp.core.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object EpochConverter {
    @SuppressLint("SimpleDateFormat")
    fun readTimestamp(timestamp: Long): String {
        val formatter = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000
        return formatter.format(calendar.time)
    }
}