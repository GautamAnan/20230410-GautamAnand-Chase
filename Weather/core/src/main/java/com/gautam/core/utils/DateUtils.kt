package com.gautam.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCurrentDate(milliSeconds: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.getDefault()) //Date and time
        return sdf.format(calendar.time)
    }

}