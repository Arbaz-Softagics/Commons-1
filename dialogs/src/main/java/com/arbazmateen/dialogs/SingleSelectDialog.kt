package com.arbazmateen.dialogs

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AlertDialog


class SingleSelectDialog<T>(private val activity: Activity, private val items: List<T>) {

    private var title: String = "Select Item"
    private var closeButtonText: String = "Close"
    private var itemListener: ((item: T, data: String, position: Int) -> Unit)? = null
    private lateinit var alertDialog: AlertDialog

    fun setItemClickListener(singleItemListener: ((item: T, data: String, position: Int) -> Unit)): SingleSelectDialog<T> {
        this.itemListener = singleItemListener
        return this
    }

    fun show() {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        val dialogView = activity.layoutInflater.inflate(R.layout.dialog_list_view_layout, null)

        val rippleViewClose = dialogView.findViewById(R.id.close) as TextView
        val titleView = dialogView.findViewById(R.id.spinnerTitle) as TextView
        val listView = dialogView.findViewById(R.id.list) as ListView
        val searchBox = dialogView.findViewById(R.id.searchBox) as EditText

        titleView.text = title
        rippleViewClose.text = closeButtonText

        val adapter = ArrayAdapter<T>(activity, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        alertDialogBuilder.setView(dialogView)

        alertDialog = alertDialogBuilder.create()

        alertDialog.setCancelable(true)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, pos, _ ->
            val t = view.findViewById(android.R.id.text1) as TextView
            val item = parent.getItemAtPosition(pos) as T
            if(itemListener != null)
                itemListener!!.invoke(item, t.text.toString(), pos)
            alertDialog.dismiss()
        }

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                adapter.filter.filter(searchBox.text.toString())
            }
        })

        rippleViewClose.setOnClickListener { alertDialog.dismiss() }
        alertDialog.show()

    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setCloseButtonText(closeButton: String) {
        closeButtonText = closeButton
    }

    class Builder<T>(activity: Activity) {

        private var items: List<T> = listOf()
        private val singleSelectListDialog = SingleSelectDialog(activity, items)

        fun setTitle(title: String) = apply { singleSelectListDialog.setTitle(title) }

        fun setItems(items: List<T>) = apply { this.items = items }

        fun setCloseButtonText(closeButton: String) = apply { singleSelectListDialog.setCloseButtonText(closeButton) }

        fun setItemClickListener(singleItemListener: ((item: T, data: String, position: Int) -> Unit)) =
                apply { singleSelectListDialog.setItemClickListener(singleItemListener) }

        fun build(): SingleSelectDialog<T> {
            return singleSelectListDialog
        }

        fun showDialog() {
            singleSelectListDialog.show()
        }
    }

}