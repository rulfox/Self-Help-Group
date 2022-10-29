package com.arany.shg.data.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {
    private const val HH_mm = "HH:mm"           //24 Hours Format
    private const val hh_mm_a = "hh:mm a"       //12 Hours Format
    private const val dd_MM_yyyy = "dd-MM-yyyy"
    private const val dd_MM_yyyy_hh_mm_a = "$dd_MM_yyyy $hh_mm_a"

    fun Date.toFormattedString(format: String = dd_MM_yyyy_hh_mm_a, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun String.toDate(format: String = dd_MM_yyyy_hh_mm_a, locale: Locale = Locale.getDefault()): Date {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.parse(this)
    }

    fun convertToFormattedDate(day: Int, month: Int, year: Int): String {
        return "$day-$month-$year"
    }

    fun convertTo24HrsTo12HrsFormattedTime(hour: Int, minute: Int): String{
        return ("$hour:$minute").convert24HrsTo12HrsTime()
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

    fun String.convert24HrsTo12HrsTime(format: String = HH_mm, locale: Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format, locale)
        val time = formatter.format(this)
        return SimpleDateFormat(hh_mm_a, locale).format(time)
    }

    fun String.convert12HrsTo24HrsTime(format: String = hh_mm_a, locale: Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format, locale)
        val time = formatter.format(this)
        return SimpleDateFormat(HH_mm, locale).format(time)
    }

    fun String.getHoursOnlyIn24HrsFormat(format: String = HH_mm, locale: Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format, locale)
        val time = formatter.format(this)
        return SimpleDateFormat("hh", locale).format(time)
    }

    fun String.getMinutesOnly(format: String = HH_mm, locale: Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format, locale)
        val time = formatter.format(this)
        return SimpleDateFormat("mm", locale).format(time)
    }

}