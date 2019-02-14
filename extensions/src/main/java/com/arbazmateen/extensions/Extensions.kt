package com.arbazmateen.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
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
fun Any.i(text: String) {
    Log.i(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.d(text: String) {
    Log.d(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.w(text: String) {
    Log.w(">>>> ${this.javaClass.simpleName}", text)
}

fun Any.e(text: String) {
    Log.e(">>>> ${this.javaClass.simpleName}", text)
}

val Any.TAG: String
    get() { return this.javaClass.simpleName }

val Any.CLASS_NAME: String
    get() { return this.javaClass.simpleName }

val Any.FULL_CLASS_NAME: String
    get() { return this.javaClass.name }

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
fun String.trimAll() = this.replace(" ", "")

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

fun String.toValidInt(default: Int = 0): Int {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toInt()
    } else {
        default
    }
}

fun String.toValidLong(default: Long = 0): Long {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toLong()
    } else {
        default
    }
}

fun String.toValidFloat(default: Float = 0.0f): Float {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toFloat()
    } else {
        default
    }
}

fun String.toValidDouble(default: Double = 0.0): Double {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toDouble()
    } else {
        default
    }
}

fun String.toValidBigDecimal(default: String = "0"): BigDecimal {
    return if(this.matches(Regex("^-?\\d+(\\.\\d+)?\$"))) {
        this.toBigDecimal()
    } else {
        default.toBigDecimal()
    }
}

fun String.append(before: String = "", after: String = "") = "$before$this$after"

/**************************************************************************
** View Extensions
**************************************************************************/
fun View.hide(condition: Boolean = true) {
    this.visibility = if(condition) View.GONE else View.VISIBLE
}

fun View.show(condition: Boolean = true) {
    this.visibility = if(condition) View.VISIBLE else View.GONE
}

fun View.isHide() = this.visibility == View.GONE
fun View.isShow() = this.visibility == View.VISIBLE

fun View.toggleVisibility() {
    if(this.isHide()) this.show() else this.hide()
}

fun View.enable(condition: Boolean = true) {
    this.isEnabled = condition
}

fun View.disable(condition: Boolean = true) {
    this.isEnabled = !condition
}

fun View.toggleEnability() {
    if(this.isEnabled) this.disable() else this.enable()
}