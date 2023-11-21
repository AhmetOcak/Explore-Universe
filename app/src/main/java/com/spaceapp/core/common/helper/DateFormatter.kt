package com.spaceapp.core.common.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    fun dateFormatter(date: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val output = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        val inputDate = input.parse(date)
        val outputDate = output.format(inputDate)

        return outputDate.toString()
    }
}