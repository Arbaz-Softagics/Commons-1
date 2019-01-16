package com.arbazmateen.dialogs

import android.content.Context

/**************************************************************************
** Model
**************************************************************************/
abstract class MultiSelectModel(protected var identity: Int, protected var isSelected: Boolean)

/**************************************************************************
** Multi Select Adaptor
**************************************************************************/
class MultiSelectAdaptor<T : MultiSelectModel>(val context: Context, dataList: MutableList<T>) {



}