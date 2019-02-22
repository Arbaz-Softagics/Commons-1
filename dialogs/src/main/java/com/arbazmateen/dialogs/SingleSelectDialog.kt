package com.arbazmateen.dialogs

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class SingleSelectDialog<T>(
    private val activity: Activity,
    private val items: List<T>,
    private var title: String = "Select Item"
) {

    private lateinit var adaptor: SimpleAdaptor<T>
    private var itemListener: ((item: T, data: String, position: Int) -> Unit)? = null
    private lateinit var alertDialog: AlertDialog
    private var searchAnywhere = false
    private var color: Int = R.color.colorAccent

    fun setItemClickListener(singleItemListener: ((item: T, data: String, position: Int) -> Unit)): SingleSelectDialog<T> {
        this.itemListener = singleItemListener
        return this
    }

    fun show() {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        val dialogView = activity.layoutInflater.inflate(R.layout.dialog_single_select_view, null)

        val titleView = dialogView.findViewById(R.id.spinnerTitle) as TextView
        val listView = dialogView.findViewById(R.id.list) as RecyclerView
        val searchBox = dialogView.findViewById(R.id.searchBox) as EditText
        val searchSwitch = dialogView.findViewById(R.id.search_switch) as Switch

        titleView.text = title

        alertDialogBuilder.setView(dialogView)
        alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(true)

        adaptor = SimpleAdaptor(activity, items)
        adaptor.setListener { item, position ->
            itemListener?.invoke(item, item.toString(), position)
            alertDialog.dismiss()
        }

        listView.adapter = adaptor

        searchSwitch.setOnCheckedChangeListener { _, checked ->
            searchAnywhere = checked
            search(searchBox.text.toString(), searchAnywhere)
        }

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(e: Editable) {
                search(searchBox.text.toString(), searchAnywhere)
            }
        })

        alertDialog.show()

    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setHigLightedColor(color: Int) {
        this.color = color
    }

    private fun search(query: String, anywhere: Boolean) {
        if (query.isNotEmpty()) {
            val filtered = items.asSequence()
                .filter {
                    if(anywhere) {
                        it.toString().toLowerCase().contains(query.toLowerCase())
                    } else {
                        it.toString().toLowerCase().startsWith(query.toLowerCase())
                    }
                }.toList()
            adaptor.changeDataList(filtered, query, color)
        } else {
            adaptor.changeDataList(items, query, color)
        }
    }

}

class SimpleAdaptor<T>(private val context: Context, private var dataList: List<T>) :
    RecyclerView.Adapter<SimpleAdaptor<T>.VH>() {

    private var queryText = ""
    private var color: Int = R.color.colorAccent
    private var listener: ((item: T, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VH {
        return VH(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(vh: VH, p: Int) {
        vh.bind(dataList[p], p)
    }

    fun setListener(listener: (item: T, position: Int) -> Unit) {
        this.listener = listener
    }

    fun changeDataList(list: List<T>, queryText: String, color: Int = R.color.colorAccent) {
        this.queryText = queryText
        this.color = color
        dataList = list
        notifyDataSetChanged()
    }

    fun onClick(i: T, p: Int) {
        listener?.invoke(i, p)
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val title = view.findViewById<TextView>(R.id.title)

        private var item: T? = null
        private var pos = -1

        init {
            view.setOnClickListener(this)
        }

        fun bind(i: T, p: Int) {
            item = i
            pos = p

            setHighlightedText(context, item.toString(), queryText, color)
        }

        override fun onClick(v: View?) {
            onClick(item!!, pos)
        }

        private fun setHighlightedText(context: Context, text: String, highlightedText: String, color: Int = R.color.colorAccent) {
            if (text.isBlank() || highlightedText.isBlank() || text.isEmpty() || highlightedText.isEmpty()) title.text = text
            else {
                val spannableString = SpannableString(text)
                val startIndex = text.toLowerCase().indexOf(highlightedText)
                if (startIndex < 0) {
                    title.text = text
                } else {
                    val endIndex = Math.min(startIndex + highlightedText.length, text.length)
                    val highlightedColor = ForegroundColorSpan(ContextCompat.getColor(context, color))
                    spannableString.setSpan(highlightedColor, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    title.text = spannableString
                }
            }
        }

    }

}