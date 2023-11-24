package com.example.jetpackdesign.util

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Util {

    companion object {

        fun String.isEmailValid(): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(this).find()
        }

        fun defaultDate(): String {
            val dateFormat = SimpleDateFormat(Constant.STANDARD_FORMAT, Locale.getDefault())
            val calendar = Calendar.getInstance()
            return dateFormat.format(calendar.timeInMillis)
        }
    }
}