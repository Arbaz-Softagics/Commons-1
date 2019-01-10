package com.arbazmateen.extensions

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    val D_MMM_YYYY = "d MMM yyyy"               // 4 Jul 2017
    val DD_MMM_YYYY = "dd MMM yyyy"             // 04 Jul 2017
    val DD_MMMM_YYYY = "dd MMMM yyyy"           // 04 July 2017
    val EEE_DD_MMMM_YYYY = "EEE, dd MMMM yyyy"  // WED, 04 July 2017
    val DD_MM_YYYY = "dd MM yyyy"               // 04 07 2017
    val DD_MM_YY = "dd MM yy"                   // 04 07 17
    val SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss" // 2017-07-17 16:05:30
}

object TimeFormat {
    val H_MM_A = "h:mm a"                   // 2:08 AM
    val HH_MM_A = "hh:mm a"                 // 02:08 AM
    val HH_MM_SS_A = "hh:mm:ss a"           // 02:08:30 AM
    val HH_MM_SS_S_A = "hh:mm:ss.S a"       // 02:08:30.956 AM
}


val Calendar.SQL_DATE_FORMAT: String
    get() = "yyyy-MM-dd HH:mm:ss"

fun Date.toString(format: String): String = SimpleDateFormat(format, Locale.US).format(this)
fun String.toDate(format: String): Date = SimpleDateFormat(format, Locale.US).parse(this)
fun Calendar.toDate(): Date = this.time
fun Calendar.setDate(date: Date) { this.time = date }

fun Date.isSame(to : Date) : Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) == sdf.format(to)
}