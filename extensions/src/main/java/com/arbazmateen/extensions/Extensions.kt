package com.arbazmateen.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
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
** EditText Extensions
**************************************************************************/
fun EditText.text(default: String = ""): String {
    return if (this.text.trim().isNotBlank()) this.text.trim().toString() else default
}

fun EditText.textCapitalize(default: String = ""): String {
    return this.text(default).capitalize()
}

/**************************************************************************
** Text View Extensions
**************************************************************************/
fun TextView.setHighlightedText(context: Context, text: String, highlightedText: String, color: Int = R.color.colorAccent) {
    val spannableString =  SpannableString(text)
    val startIndex = text.toLowerCase().indexOf(highlightedText)
    val endIndex = startIndex + highlightedText.length
    val highlightedColor = ColorStateList(arrayOf(intArrayOf()), intArrayOf(ContextCompat.getColor(context, color)))
    val textAppearanceSpan = TextAppearanceSpan(null, Typeface.NORMAL, -1, highlightedColor, null)
    spannableString.setSpan(textAppearanceSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = spannableString
}

/**************************************************************************
** Numbers Extensions
**************************************************************************/
fun Float.format(precisionCount: Int = 2, default: String = "0"): String {
    var per = ""
    (1..precisionCount).forEach { per += "#" }
    val value = DecimalFormat("#.$per").format(this) ?: default
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Float.formatNoComma(precisionCount: Int = 2, default: String = "0"): String {
    var per = ""
    (1..precisionCount).forEach { per += "#" }
    val value = DecimalFormat("#.$per").format(this) ?: default
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Double.format(precisionCount: Int = 2, default: String = "0"): String {
    var per = ""
    (1..precisionCount).forEach { per += "#" }
    val value = DecimalFormat("#.$per").format(this) ?: default
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Double.formatNoComma(precisionCount: Int = 2, default: String = "0"): String {
    var per = ""
    (1..precisionCount).forEach { per += "#" }
    val value = DecimalFormat("#.$per").format(this) ?: default
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun Long.format(default: String = "0"): String {
    return DecimalFormat("#,###").format(this) ?: default
}

fun Int.format(default: String = "0"): String {
    return DecimalFormat("#,###").format(this) ?: default
}

fun BigInteger.format(default: String = "0"): String {
    return DecimalFormat("#,###").format(this) ?: default
}

fun BigDecimal.format(default: String = "0"): String {
    val value = DecimalFormat("#,###.##").format(this) ?: default
    if(value.endsWith(".")) value.replace(".", "")
    return value
}

fun BigDecimal.formatNoComma(precisionCount: Int = 2, default: String = "0"): String {
    var per = ""
    (1..precisionCount).forEach { per += "#" }
    val value = DecimalFormat("#.$per").format(this) ?: default
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

fun String.check(with: String, back: String, default: String = ""): String {
    if (this == with) {
        return back
    }
    return default
}

fun String.toDecimal(default: String = "0"): BigDecimal {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toBigDecimal()
    } else {
        default.toBigDecimal()
    }
}

/**************************************************************************
** View Extensions
**************************************************************************/
fun View.hide(condition: Boolean = true) {
    if(condition) this.visibility = View.GONE
}

fun View.show(condition: Boolean = true) {
    if(condition) this.visibility = View.VISIBLE
}

fun View.enable(condition: Boolean = true) {
    if(condition) this.isEnabled = true
}

fun View.disable(condition: Boolean = true) {
    if(condition) this.isEnabled = false
}