package com.arbazmateen.dialogs

import android.util.Log

/**************************************************************************
** modern try catch function
**************************************************************************/
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {
    Log.e("ERROR", e.message)
}

tailrec fun findIn(list: List<Long>, startIndex: Int, lastIndex: Int, searchItem: Long): Pair<Int, Boolean> {

    if (lastIndex >= startIndex) {
        val index = startIndex + (lastIndex - startIndex) / 2

        if (list[index] == searchItem) return Pair(index, true)

        return if (list[index] > searchItem)
            findIn(list, startIndex, index - 1, searchItem)
        else
            findIn(list, index + 1, lastIndex, searchItem)
    }

    return Pair(-1, false)
}