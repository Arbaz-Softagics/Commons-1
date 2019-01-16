package com.arbazmateen.extensions

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DecimalFormat

/**************************************************************************
** Any extensions
**************************************************************************/
fun Any.info(text: String) {
    Log.i(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.debug(text: String) {
    Log.d(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.warning(text: String) {
    Log.w(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.error(text: String) {
    Log.e(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.getColor(context: Context, colorId: Int): Int {
    return ContextCompat.getColor(context, colorId)
}

fun Any.toastLong(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Any.toastShort(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

/**************************************************************************
** Edittext Extensions
**************************************************************************/
fun EditText.text(): String {
    return if (this.text.trim().isNotBlank()) this.text.trim().toString() else ""
}

fun EditText.textCapitalize(): String {
    return this.text().capitalize()
}

/**************************************************************************
** Numbers Extensions
**************************************************************************/
fun Float.format(): String {
    val value = DecimalFormat("#,###.###").format(this.toDouble()) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Float.formatNoComma(): String {
    val value = DecimalFormat("#.###").format(this.toDouble()) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Double.format(): String {
    val value = DecimalFormat("#,###.##").format(this) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Double.formatNoComma(): String {
    val value = DecimalFormat("#.###").format(this) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Long.format(): String {
    return DecimalFormat("#,###").format(this) ?: "0"
}

fun Int.format(): String {
    return DecimalFormat("#,###").format(this) ?: "0"
}

fun BigInteger.format(): String {
    return DecimalFormat("#,###").format(this) ?: "0"
}

fun BigDecimal.format(): String {
    val value = DecimalFormat("#,###.##").format(this) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun BigDecimal.formatNoComma(): String {
    val value = DecimalFormat("#.##").format(this) ?: "0.0"
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

/**************************************************************************
** Boolean Extensions
**************************************************************************/
fun Boolean.toInt() = if (this) 1 else 0
fun Int.toBool() = this == 1

/**************************************************************************
** Other Extensions
**************************************************************************/
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {
    Log.e("ERROR", e.message)
}

/**************************************************************************
** String Extensions
**************************************************************************/
inline fun String.check(with: String, function: () -> String): String {
    if (this == with) {
        return function.invoke()
    }
    return ""
}

fun String.check(with: String, back: String): String {
    if (this == with) {
        return back
    }
    return ""
}

fun String.toDecimal(): BigDecimal {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toBigDecimal()
    } else {
        "0".toBigDecimal()
    }
}
