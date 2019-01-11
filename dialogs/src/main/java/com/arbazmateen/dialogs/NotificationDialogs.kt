package com.arbazmateen.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

val INFO_ICON = R.drawable.ic_info
val WARNING_ICON = R.drawable.ic_warning
val ERROR_ICON = R.drawable.ic_error
val DELETE_ICON = R.drawable.ic_delete

fun dialog(context: Context, title: Int, message: String, cancelAble: Boolean, icon: Int): AlertDialog.Builder {
    val dialogBuilder = AlertDialog.Builder(context)
    return with(dialogBuilder) {
        setTitle(title)
        setMessage(message)
        setCancelable(cancelAble)
        setIcon(icon)
    }
}

fun dialog(context: Context, title: String, message: String, cancelAble: Boolean, icon: Int): AlertDialog.Builder {
    val dialogBuilder = AlertDialog.Builder(context)
    return with(dialogBuilder) {
        setTitle(title)
        setMessage(message)
        setCancelable(cancelAble)
        setIcon(icon)
    }
}

fun infoDialog(context: Context,
               message: String,
               title: Int = R.string.title_info,
               cancelAble: Boolean = false,
               icon: Int = INFO_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun warningDialog(context: Context,
               message: String,
               title: Int = R.string.title_warning,
               cancelAble: Boolean = false,
               icon: Int = WARNING_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun errorDialog(context: Context,
                message: String,
                title: Int = R.string.title_error,
                cancelAble: Boolean = false,
                icon: Int = ERROR_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun confirmDelete(context: Context,
                message: String,
                title: Int = R.string.title_confirm_delete,
                cancelAble: Boolean = false,
                icon: Int = DELETE_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun infoDialog(context: Context,
               title: String,
               message: String,
               cancelAble: Boolean = false,
               icon: Int = INFO_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun warningDialog(context: Context,
                  title: String,
                  message: String,
                  cancelAble: Boolean = false,
                  icon: Int = WARNING_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun errorDialog(context: Context,
                title: String,
                message: String,
                cancelAble: Boolean = false,
                icon: Int = ERROR_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun confirmDelete(context: Context,
                  title: String,
                  message: String,
                  cancelAble: Boolean = false,
                  icon: Int = DELETE_ICON): AlertDialog.Builder {
    return dialog(context, title, message, cancelAble, icon)
}

fun waitDialog(context: Context, message: String = "Please wait a moment...", cancelAble: Boolean = false): AlertDialog {
    val dialog = AlertDialog.Builder(context)
    val view = LayoutInflater.from(context).inflate(R.layout.progross_dialog_layout, null)

    val textView = view.findViewById<TextView>(R.id.message_text)

    textView.text = message

    dialog.setCancelable(cancelAble)
    dialog.setView(view)

    return dialog.create()
}