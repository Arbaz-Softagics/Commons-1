package com.arbazmateen.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog

const val INFO_ICON = 0
const val WARNING_ICON = 0
const val ERROR_ICON = 0
const val DELETE_ICON = 0

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
