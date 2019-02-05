package com.arbazmateen.adaptors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): RecyclerView(context, attrs, defStyle) {

    companion object STATE {
        const val NONE: Byte = 0
        const val LOADING: Byte = 1
        const val LIST: Byte = 2
        const val EMPTY: Byte = 3
        const val ADD: Byte = 4
        const val ERROR: Byte = 5
        const val RETRY: Byte = 6
    }

    private var state = NONE

    private var loadingView: View? = null
    private var emptyView: View? = null
    private var addView: View? = null
    private var errorView: View? = null
    private var retryView: View? = null


    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() { checkIfEmpty() }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) { checkIfEmpty() }
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) { checkIfEmpty() }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        getAdapter()?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

    fun setLoadingView(loadingView: View) = apply {
        this.loadingView = loadingView
    }

    fun setEmptyView(emptyView: View) = apply {
        this.emptyView = emptyView
    }

    fun setAddView(addView: View) = apply {
        this.addView = addView
    }

    fun setErrorView(errorView: View) = apply {
        this.errorView = errorView
    }

    fun setRetryView(retryView: View) = apply {
        this.retryView = retryView
    }

    fun loading() {
        setState(LOADING)
    }

    fun empty(addItem: Boolean = false) {
        if(addItem)
            setState(ADD)
        else
            setState(EMPTY)
    }

    fun error(retry: Boolean = false) {
        if(retry)
            setState(RETRY)
        else
            setState(ERROR)
    }

    fun setState(state: Byte) {
        this.state = state
        switchState()
    }

    private fun checkIfEmpty() {
        if(emptyView != null && adapter != null) {
            if(adapter?.itemCount == 0) {
                setState(EMPTY)
            } else {
                setState(LIST)
            }
        }
    }

    private fun switchState() {
        when(state) {
            LOADING -> {
                if(loadingView != null) {
                    show(loadingView)
                }
            }
            LIST -> {
                hideAll()
            }
            EMPTY -> {
                if(emptyView != null) {
                    show(emptyView)
                }
            }
            ADD -> {
                if(addView != null) {
                    show(addView)
                }
            }
            ERROR -> {
                if(errorView != null) {
                    show(errorView)
                }
            }
            RETRY -> {
                if(retryView != null) {
                    show(retryView)
                }
            }
            else -> {
                hide()
            }
        }
    }

    private fun hide() {
        visibility = View.GONE
    }

    private fun show() {
        visibility = View.VISIBLE
    }

    private fun hide(view: View?) {
        view?.visibility = View.GONE
        show()
    }

    private fun hideAll() {
        hide(loadingView)
        hide(emptyView)
        hide(addView)
        hide(errorView)
        hide(retryView)
        show()
    }

    private fun show(view: View?) {
        hide()
        view?.visibility = View.VISIBLE
    }
}