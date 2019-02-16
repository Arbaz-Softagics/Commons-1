package com.arbazmateen.extensions

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    val D_MM_YY = "d MM yy"                     // 4 07 17
    val DD_MM_YY = "dd MM yy"                   // 04 07 17
    val D_MMM_YY = "d MMM yy"                   // 4 Jul 17
    val D_MMM_YYYY = "d MMM yyyy"               // 4 Jul 2017
    val DD_MM_YYYY = "dd MM yyyy"               // 04 07 2017
    val DD_MMM_YY = "dd MMM yy"                 // 04 Jul 17
    val DD_MMM_YYYY = "dd MMM yyyy"             // 04 Jul 2017
    val DD_MMMM_YY = "dd MMMM yy"               // 04 July 17
    val DD_MMMM_YYYY = "dd MMMM yyyy"           // 04 July 2017
    val EEE_DD_MM_YY = "EEE, dd MM yy"          // WED, 04 07 17
    val EEE_DD_MM_YYYY = "EEE, dd MM yyyy"      // WED, 04 07 2017
    val EEE_D_MMM_YYYY = "EEE, d MMM yyyy"      // WED, 4 Jul 2017
    val EEE_DD_MMM_YYYY = "EEE, dd MMM yyyy"    // WED, 04 Jul 2017
    val EEE_DD_MMMM_YYYY = "EEE, dd MMMM yyyy"  // WED, 04 July 2017

    val SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss" // 2017-07-17 16:05:30

    val SQL_START_DATE_FORMAT = "yyyy-MM-dd 00:00:00" // 2017-07-17 16:05:30
    val SQL_END_DATE_FORMAT = "yyyy-MM-dd 59:59:59" // 2017-07-17 16:05:30
}

object TimeFormat {
    val h_mm_a = "h:mm a"                   // 2:08 AM
    val hh_mm_a = "hh:mm a"                 // 02:08 AM
    val hh_mm_ss_a = "hh:mm:ss a"           // 02:08:30 AM
    val hh_mm_ss_S_a = "hh:mm:ss.S a"       // 02:08:30.956 AM
    val HH_mm = "HH:mm"                     // 21:08
    val HH_mm_ss = "HH:mm:ss"               // 21:08:30
}

fun Date.toString(format: String): String = SimpleDateFormat(format, Locale.US).format(this)
fun String.toDate(format: String): Date = SimpleDateFormat(format, Locale.US).parse(this)
fun Calendar.toDate(): Date = this.time
fun Calendar.setDate(date: Date) = apply {
    this.time = date
}

fun Calendar.plusDays(days: Int) = apply {
    this.add(Calendar.DATE, days)
    return this
}

fun Calendar.plusMonths(months: Int) = apply {
    this.add(Calendar.MONTH, months)
    return this
}

fun Calendar.plusYears(years: Int) = apply {
    this.add(Calendar.YEAR, years)
    return this
}

fun Calendar.firstDayOfMonth(months: Int = 0) = apply {
    this.set(Calendar.DAY_OF_MONTH, 1)
    this.add(Calendar.MONTH, months)
    return this
}

fun Calendar.lastDayOfMonth(months: Int = 0) = apply {
    this.set(Calendar.DAY_OF_MONTH, 1)
    this.add(Calendar.MONTH, months)
    this.set(Calendar.DATE, this.getActualMaximum(Calendar.DATE))
    return this
}

fun Date.plusDays(days: Int) = apply {
    val date = Calendar.getInstance()
    date.setDate(this).add(Calendar.DATE, days)
    return date.time
}

fun Date.plusMonths(months: Int) = apply {
    val date = Calendar.getInstance()
    date.setDate(this).add(Calendar.MONTH, months)
    return date.time
}

fun Date.plusYears(years: Int) = apply {
    val date = Calendar.getInstance()
    date.setDate(this).add(Calendar.YEAR, years)
    return date.time
}

fun Date.firstDayOfMonth(months: Int = 0) = apply {
    val date = Calendar.getInstance()
    date.setDate(this)
    date.set(Calendar.DAY_OF_MONTH, 1)
    date.add(Calendar.MONTH, months)
    return date.time
}

fun Date.lastDayOfMonth(months: Int = 0) = apply {
    val date = Calendar.getInstance()
    date.setDate(this)
    date.set(Calendar.DAY_OF_MONTH, 1)
    date.add(Calendar.MONTH, months)
    date.set(Calendar.DATE, date.getActualMaximum(Calendar.DATE))
    return date.time
}

fun Date.isSame(to: Date): Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) == sdf.format(to)
}

fun Date.isBefore(to: Date): Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) < sdf.format(to)
}

fun Date.isAfter(to: Date): Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) > sdf.format(to)
}

fun Date.compare(to: Date): Int {
    return when {
        this.isSame(to) -> 0
        this.isAfter(to) -> 1
        else -> -1
    }
}

fun Date.differenceWith(date: Date?): String {

    if(date == null) return ""

    val diff = this.time - date.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = months / 12

    if(years > 0) return "$years year(s) ago"
    if(months > 0) return "$months month(s) ago"
    if(days > 0) return "$days day(s) ago"
    if(hours > 0) return "$hours hour(s) ago"
    if(minutes > 0) return "$minutes minute(s) ago"
    return "$seconds second(s) ago"
}

fun Date.difference(): String {

    val now = Date().time
    val diff = now - this.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = months / 12

    if(years > 0) return "$years year(s) ago"
    if(months > 0) return "$months month(s) ago"
    if(days > 0) return "$days day(s) ago"
    if(hours > 0) return "$hours hour(s) ago"
    if(minutes > 0) return "$minutes minute(s) ago"
    return "$seconds second(s) ago"
}