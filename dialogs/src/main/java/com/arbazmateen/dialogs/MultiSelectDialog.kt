package com.arbazmateen.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable


/**************************************************************************
** Model
**************************************************************************/
abstract class MultiSelectModel(open var id: Long,
                                open var displayName: String,
                                open var isSelected: Boolean = false): Serializable, Parcelable

/**************************************************************************
** Multi Select Adaptor
**************************************************************************/
class MultiSelectAdaptor<T : MultiSelectModel>(val context: Context, private val dataList: MutableList<T>):
    RecyclerView.Adapter<MultiSelectAdaptor<T>.ViewHolder>() {

    val selectedIdsList = mutableListOf<Long>()
    val selectedItemsList = mutableListOf<T>()
    private var mOnDataBindListener: ((textView: TextView, item: T, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dialog_multi_select, parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    fun setDataBindListener(onDataBindListener: (textView: TextView, item: T, position: Int) -> Unit) =
        apply { this.mOnDataBindListener = onDataBindListener }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private var item: T? = null
        private var pos = -1

        private val checkBox = view.findViewById<CheckBox>(R.id.checkbox1)
        private val text = view.findViewById<TextView>(R.id.text1)

        init {
            view.setOnClickListener(this)
        }

        fun bind(i: T, position: Int) {
            this.item = i
            pos = position

            if(item is MultiSelectModel) {
                item!!.isSelected = !item!!.isSelected
                checkBox.isChecked = item!!.isSelected
                if(item!!.isSelected) {
                    if(!selectedIdsList.contains(item!!.id)) {
                        selectedIdsList.add(item!!.id)
                        selectedItemsList.add(item!!)
                    }
                } else {
                    if(selectedIdsList.contains(item!!.id)) {
                        selectedIdsList.remove(item!!.id)
                        selectedItemsList.remove(item!!)
                    }
                }
            }

            if (mOnDataBindListener != null) {
                mOnDataBindListener?.invoke(text, i, pos)
            }
        }

        override fun onClick(p0: View?) {
            if(item is MultiSelectModel) {
                item!!.isSelected = !item!!.isSelected
                checkBox.isChecked = item!!.isSelected
                if(item!!.isSelected) {
                    selectedIdsList.add(item!!.id)
                    selectedItemsList.add(item!!)
                } else {
                    selectedIdsList.remove(item!!.id)
                    selectedItemsList.remove(item!!)
                }
            }
        }
    }
}

/**************************************************************************
** Multi Select Dialog Fragment
**************************************************************************/
class MultiSelectDialog<T : MultiSelectModel>: AppCompatDialogFragment(),
    SearchView.OnQueryTextListener, View.OnClickListener {

    private var onSubmitClickListener: ((selectedIds: List<Long>,
                                         selectedItems: List<T>,
                                         stringData: String) -> Unit)? = null
    private var onCloseClickListener: (() -> Unit)? = null

    private lateinit var mContext: Context

    private var mainDataList = mutableListOf<T>()
    private var preSelectedIds = mutableListOf<Int>()
    private var selectedItems = mutableListOf<T>()

    private var title: String = "Multi Selection"
    private var submitText: String = "DONE"
    private var closeText: String = "CLOSE"

    fun with(context: Context) = apply { mContext = context }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(mContext)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setFlags(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        dialog.setContentView(R.layout.dialog_multi_select_view)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val recyclerView = dialog.findViewById<RecyclerView>(R.id.recycler_view)
        val searchView = dialog.findViewById<SearchView>(R.id.search_view)
        val dialogTitle = dialog.findViewById<TextView>(R.id.title)
        val dialogSubmit = dialog.findViewById<TextView>(R.id.done)
        val dialogCancel = dialog.findViewById<TextView>(R.id.cancel)

        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        dialogTitle.text = title
        dialogSubmit.setOnClickListener(this)
        dialogCancel.setOnClickListener(this)

        searchView.setOnQueryTextListener(this)
        searchView.onActionViewExpanded()
        searchView.clearFocus()

        return dialog
    }

    override fun onQueryTextSubmit(query: String?): Boolean { return false }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.done -> {

            }
            R.id.cancel -> {

            }
            R.id.select_all_container -> {

            }
        }
    }
}