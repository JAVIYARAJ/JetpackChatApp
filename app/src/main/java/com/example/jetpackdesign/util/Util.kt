package com.example.jetpackdesign.util

import android.os.Build
import android.util.Log
import android.util.Patterns
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
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

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentMonthDays(year: Int, month: Int): Int {
            val currentYear = YearMonth.of(year, month)
            return currentYear.lengthOfMonth()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDayOfMonth(year: Int, month: Int): String {
            val firstDayOfMonth = LocalDate.of(year, month, 1)
            val dayOfWeek = firstDayOfMonth.dayOfWeek
            return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getMonth(): Int {
            val currentDate = LocalDate.now()
            return currentDate.monthValue
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getYear(): Int {
            val currentDate = LocalDate.now()
            return currentDate.year
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDay(): Int {
            val currentDate = LocalDate.now()
            return currentDate.dayOfMonth
        }

    }
}