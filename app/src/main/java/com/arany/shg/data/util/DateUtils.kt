package com.arany.shg.data.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    private const val hh_mm_a = "hh:mm a"
    private const val dd_MM_yyyy = "dd-MM-yyyy"
    private const val dd_MM_yyyy_hh_mm_a = "$dd_MM_yyyy $hh_mm_a"

    fun Date.toString(format: String = dd_MM_yyyy_hh_mm_a, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun Date.getTimeOnly(locale: Locale = Locale.getDefault()): String{
        return SimpleDateFormat(hh_mm_a, locale).format(this)
    }

    fun Date.getDateOnly(locale: Locale = Locale.getDefault()): String{
        return SimpleDateFormat(dd_MM_yyyy, locale).format(this)
    }

    fun getCurrentDateOnly() = getCurrentDateTime().getDateOnly()

    fun getCurrentTimeOnly() = getCurrentDateTime().getTimeOnly()
}