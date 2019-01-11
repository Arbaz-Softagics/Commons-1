package com.arbazmateen.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


@FunctionalInterface
interface OnDataBindListener<T> {
    fun onDataBind(view: View, item: T, position: Int, viewMap: Map<Int, View>)
}

@FunctionalInterface
interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}

@FunctionalInterface
interface OnItemLongClickListener<T> {
    fun onItemLongClick(item: T, position: Int)
}

@FunctionalInterface
interface OnOptionMenuClickListener<T> {
    fun onOptionMenuClick(popUpMenu: PopupMenu, item: T, position: Int)
}

@FunctionalInterface
interface OnItemChildClickListener<T> {
    fun onItemChildClick(item: T, position: Int, view: View)
}

enum class LayoutType {
    LIST, GRID, STAGGERED
}

object Orientation {
    const val VERTICAL = RecyclerView.VERTICAL
    const val HORIZONTAL = RecyclerView.HORIZONTAL
}


class SimpleRecyclerAdaptor<T> private constructor(
    private val context: Context,
    private var dataList: MutableList<T>,
    private var layout: Int,
    private var viewsList: MutableList<Int> = mutableListOf(),
    private var clickableItem: Boolean = false,
    private var longClickableItem: Boolean = false,
    private var hasOptionMenu: Boolean = false,
    private var optionMenu: Int = 0,
    private var optionMenuViewId: Int = 0
) : RecyclerView.Adapter<SimpleRecyclerAdaptor<T>.ViewHolder>() {

    private var onDataBindListener: OnDataBindListener<T>? = null
    private var onItemClickListener: OnItemClickListener<T>? = null
    private var onItemLongClickListener: OnItemLongClickListener<T>? = null
    private var onOptionMenuClickListener: OnOptionMenuClickListener<T>? = null
    private var onItemChildClickListener: OnItemChildClickListener<T>? = null

    private var mOnDataBindListener: ((view: View, item: T, position: Int, viewMap: Map<Int, View>) -> Unit)? = null
    private var mOnItemClickListener: ((item: T, position: Int) -> Unit)? = null
    private var mOnItemLongClickListener: ((item: T, position: Int) -> Unit)? = null
    private var mOnOptionMenuClickListener: ((popUpMenu: PopupMenu, item: T, position: Int) -> Unit)? = null
    private var mOnItemChildClickListener: ((item: T, position: Int, view: View) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    fun changeDataList(list: MutableList<T>) {
        dataList = list
        notifyDataSetChanged()
    }

    fun removeItemAtPosition(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(data: T, position: Int) {
        dataList[position] = data
        notifyItemChanged(position)
    }

    fun getItem(position: Int) = dataList[position]

    fun updateItemState(position: Int, changes: T.() -> Unit) {
        dataList[position].changes()
        notifyItemChanged(position)
    }

    fun setDataBindListener(onDataBindListener: OnDataBindListener<T>) =
        apply { this.onDataBindListener = onDataBindListener }

    fun setDataBindListener(onDataBindListener: (view: View, item: T, position: Int, viewMap: Map<Int, View>) -> Unit):
            SimpleRecyclerAdaptor<T> {
        this.mOnDataBindListener = onDataBindListener
        return this
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener<T>) =
        apply { this.onItemClickListener = onItemClickListener }

    fun setItemClickListener(onItemClickListener: (item: T, position: Int) -> Unit) =
        apply { this.mOnItemClickListener = onItemClickListener }

    fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<T>) =
        apply { this.onItemLongClickListener = onItemLongClickListener }

    fun setItemLongClickListener(onItemLongClickListener: (item: T, position: Int) -> Unit) =
        apply { this.mOnItemLongClickListener = onItemLongClickListener }

    fun setItemChildClickListener(onItemChildClickListener: OnItemChildClickListener<T>) =
        apply { this.onItemChildClickListener = onItemChildClickListener }

    fun setItemChildClickListener(onItemChildClickListener: (item: T, position: Int, view: View) -> Unit) =
        apply { this.mOnItemChildClickListener = onItemChildClickListener }

    fun setOptionMenuClickListener(onOptionMenuClickListener: OnOptionMenuClickListener<T>) =
        apply { this.onOptionMenuClickListener = onOptionMenuClickListener }

    fun setOptionMenuClickListener(onOptionMenuClickListener: (popUpMenu: PopupMenu, item: T, position: Int) -> Unit) =
        apply { this.mOnOptionMenuClickListener = onOptionMenuClickListener }

    private fun onDataBind(view: View, item: T, position: Int, viewMap: Map<Int, View>) {
        onDataBindListener?.onDataBind(view, item, position, viewMap)
    }

    private fun onItemClick(data: T, position: Int) {
        onItemClickListener?.onItemClick(data, position)
    }

    private fun onItemLongClick(data: T, position: Int) {
        onItemLongClickListener?.onItemLongClick(data, position)
    }

    private fun onOptionMenuClickListener(popUpMenu: PopupMenu, item: T, position: Int) {
        onOptionMenuClickListener?.onOptionMenuClick(popUpMenu, item, position)
    }

    private fun onItemChildClick(data: T, position: Int, view: View) {
        onItemChildClickListener?.onItemChildClick(data, position, view)
    }

    class Builder<T>(private val context: Context) {

        private var dataList: MutableList<T> = mutableListOf()
        private var viewsList: MutableList<Int> = mutableListOf()
        private var layout = 0
        private var optionMenu = 0
        private var optionMenuViewId = 0
        private var clickableItem = false
        private var longClickableItem = false
        private var hasOptionMenu = false

        private var onDataBindListener: OnDataBindListener<T>? = null
        private var onItemClickListener: OnItemClickListener<T>? = null
        private var onItemLongClickListener: OnItemLongClickListener<T>? = null
        private var onOptionMenuClickListener: OnOptionMenuClickListener<T>? = null
        private var onItemChildClickListener: OnItemChildClickListener<T>? = null

        private var mOnDataBindListener: ((view: View, item: T, position: Int, viewMap: Map<Int, View>) -> Unit)? = null
        private var mOnItemClickListener: ((item: T, position: Int) -> Unit)? = null
        private var mOnItemLongClickListener: ((item: T, position: Int) -> Unit)? = null
        private var mOnOptionMenuClickListener: ((popUpMenu: PopupMenu, item: T, position: Int) -> Unit)? = null
        private var mOnItemChildClickListener: ((item: T, position: Int, view: View) -> Unit)? = null

        fun setDataList(dataList: MutableList<T>) = apply { this.dataList = dataList }
        fun setLayout(layout: Int) = apply { this.layout = layout }
        fun addView(resId: Int) = apply { viewsList.add(resId) }
        fun addViews(vararg resId: Int) = apply { resId.forEach { viewsList.add(it) } }
        fun addViews(list: List<Int>) = apply { viewsList = list as MutableList<Int> }

        fun setOptionMenu(optionMenuViewId: Int, menu: Int) =
            apply { hasOptionMenu = true; this.optionMenuViewId = optionMenuViewId; this.optionMenu = menu }

        fun setBindViewListener(onDataBindListener: OnDataBindListener<T>) =
            apply { this.onDataBindListener = onDataBindListener }

        fun setBindViewListener(onDataBindListener: (view: View, item: T, position: Int, viewMap: Map<Int, View>) -> Unit) =
            apply { this.mOnDataBindListener = onDataBindListener }

        fun setItemClickListener(onItemClickListener: OnItemClickListener<T>) =
            apply { clickableItem = true; this.onItemClickListener = onItemClickListener }

        fun setItemClickListener(onItemClickListener: (item: T, position: Int) -> Unit) =
            apply { this.mOnItemClickListener = onItemClickListener }

        fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<T>) =
            apply { longClickableItem = true; this.onItemLongClickListener = onItemLongClickListener }

        fun setItemLongClickListener(onItemLongClickListener: (item: T, position: Int) -> Unit) =
            apply { this.mOnItemLongClickListener = onItemLongClickListener }

        fun setItemChildClickListener(onItemChildClickListener: OnItemChildClickListener<T>) =
            apply { this.onItemChildClickListener = onItemChildClickListener }

        fun setItemChildClickListener(onItemChildClickListener: (item: T, position: Int, view: View) -> Unit) =
            apply { this.mOnItemChildClickListener = onItemChildClickListener }

        fun setOptionClickListener(onOptionMenuClickListener: OnOptionMenuClickListener<T>) =
            apply { this.onOptionMenuClickListener = onOptionMenuClickListener }

        fun setOptionMenuClickListener(onOptionMenuClickListener: (popUpMenu: PopupMenu, item: T, position: Int) -> Unit) =
            apply { this.mOnOptionMenuClickListener = onOptionMenuClickListener }

        fun build(): SimpleRecyclerAdaptor<T> {
            return initAdaptor()
        }

        fun into(recyclerView: RecyclerView) {
            val adaptor = initAdaptor()
            recyclerView.adapter = adaptor
        }

        fun into(recyclerView: RecyclerView, block: RecyclerView.() -> Unit) {
            val adaptor = initAdaptor()
            recyclerView.block()
            recyclerView.adapter = adaptor
        }

        fun into(
            recyclerView: RecyclerView, layoutType: LayoutType = LayoutType.LIST, spanCount: Int = 2,
            orientation: Int = Orientation.VERTICAL, divider: Boolean = false
        ) {
            val adaptor = initAdaptor()

            if (divider && layoutType == LayoutType.LIST) {
                recyclerView.addItemDecoration(Divider(context))
            }

            when (layoutType) {
                LayoutType.LIST -> {
                    recyclerView.layoutManager = LinearLayoutManager(context)
                }
                LayoutType.GRID -> {
                    if (recyclerView.itemDecorationCount > 0) recyclerView.removeItemDecorationAt(0)
                    recyclerView.layoutManager = GridLayoutManager(context, spanCount, orientation, false)
                }
                LayoutType.STAGGERED -> {
                    if (recyclerView.itemDecorationCount > 0) recyclerView.removeItemDecorationAt(0)
                    recyclerView.layoutManager = StaggeredGridLayoutManager(spanCount, orientation)
                }
            }

            recyclerView.adapter = adaptor
        }

        private fun initAdaptor(): SimpleRecyclerAdaptor<T> {
            val adaptor = SimpleRecyclerAdaptor(
                context, dataList, layout, viewsList,
                clickableItem, longClickableItem, hasOptionMenu, optionMenu, optionMenuViewId
            )

            if (onDataBindListener != null) adaptor.setDataBindListener(onDataBindListener!!)
            if (mOnDataBindListener != null) adaptor.setDataBindListener(mOnDataBindListener!!)

            if (onItemClickListener != null) adaptor.setItemClickListener(onItemClickListener!!)
            if (mOnItemClickListener != null) adaptor.setItemClickListener(mOnItemClickListener!!)

            if (onItemLongClickListener != null) adaptor.setItemLongClickListener(onItemLongClickListener!!)
            if (mOnItemLongClickListener != null) adaptor.setItemLongClickListener(mOnItemLongClickListener!!)

            if (onItemChildClickListener != null) adaptor.setItemChildClickListener(onItemChildClickListener!!)
            if (mOnItemChildClickListener != null) adaptor.setItemChildClickListener(mOnItemChildClickListener!!)

            if (onOptionMenuClickListener != null) adaptor.setOptionMenuClickListener(onOptionMenuClickListener!!)
            if (mOnOptionMenuClickListener != null) adaptor.setOptionMenuClickListener(mOnOptionMenuClickListener!!)

            return adaptor
        }

    }

    inner class ViewHolder(private val context: Context, private val view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

        private var viewMap: MutableMap<Int, View> = mutableMapOf()
        private var item: T? = null
        private var pos: Int = 0

        init {
            if (clickableItem) {
                view.setOnClickListener(this)
            }

            if (longClickableItem) {
                view.setOnLongClickListener(this)
            }

            if (hasOptionMenu && optionMenu != 0 && optionMenuViewId != 0) {

                val menu = view.findViewById<View>(optionMenuViewId)
                menu.visibility = View.VISIBLE
                menu.setOnClickListener { om ->
                    val popUpMenu = PopupMenu(context, om)
                    popUpMenu.inflate(optionMenu)

                    mOnOptionMenuClickListener?.invoke(popUpMenu, item!!, pos)

                }

            }

            viewsList.forEach {
                viewMap[it] = view.findViewById(it)
            }
        }

        fun bind(t: T, p: Int) {
            item = t
            pos = p
            if (mOnDataBindListener != null) {
                mOnDataBindListener?.invoke(view, t, p, viewMap)
            } else {
                onDataBind(view, t, p, viewMap)
            }
        }

        override fun onClick(v: View?) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener?.invoke(item!!, pos)
            } else {
                onItemClick(item!!, pos)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener?.invoke(item!!, pos)
            } else {
                onItemLongClick(item!!, pos)
            }
            return true
        }
    }

}