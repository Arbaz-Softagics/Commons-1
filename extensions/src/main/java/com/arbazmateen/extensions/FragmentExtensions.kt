package com.arbazmateen.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.Serializable


fun Fragment.toastLong(text: String) {
    Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
}

fun Fragment.toastShort(text: String) {
    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.requestFocus(view: View) {
    if (view.requestFocus()) {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }
}

fun Fragment.hideKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.getIntValue(key: String, default: Int = 0): Int {
    if(arguments != null && arguments!!.containsKey(key)) {
        return arguments?.getInt(key, default) ?: default
    }
    return default
}

fun Fragment.getLongValue(key: String, default: Long = 0L): Long {
    if(arguments != null && arguments!!.containsKey(key)) {
        return arguments?.getLong(key, default) ?: default
    }
    return default
}

fun Fragment.getStringValue(key: String, default: String = ""): String {
    if(arguments != null && arguments!!.containsKey(key)) {
        return arguments?.getString(key, default) ?: default
    }
    return default
}

inline fun <reified T : Serializable> Fragment.getSerializable(key: String): T? {
    if(arguments != null && arguments!!.containsKey(key)) {
        try {
            return arguments?.getSerializable(key) as T
        } catch (e: Exception) {
            Log.e("ERROR", e.message)
        }
    }
    return null
}

fun Fragment.getColor(context: Context, colorId: Int): Int {
    return ContextCompat.getColor(context, colorId)
}

fun Fragment.copyToClipboard(context: Context, text: String, msg: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.primaryClip = ClipData.newPlainText("Accounts", text)
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}