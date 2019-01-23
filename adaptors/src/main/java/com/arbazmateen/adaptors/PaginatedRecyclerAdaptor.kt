package com.arbazmateen.adaptors

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class PaginatedRecyclerAdaptor<T> {

    companion object {
        const val ITEM = 0
        const val LOADING = 1
        const val ERROR = 2
    }




    inner class PaginatedViewHolder(private val context: Context, private val view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

        private var viewMap: MutableMap<Int, View> = mutableMapOf()
        private var item: T? = null
        private var pos: Int = 0

        init {

        }

        fun bind(t: T, p: Int) {
            item = t
            pos = p

        }

        override fun onClick(v: View?) {
        }

        override fun onLongClick(v: View?): Boolean {
            return true
        }
    }

}