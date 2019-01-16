package com.arbazmateen.dialogs

import android.util.Log

/**************************************************************************
** modern try catch function
**************************************************************************/
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {
    Log.e("ERROR", e.message)
}