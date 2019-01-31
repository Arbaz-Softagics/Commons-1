package com.arbazmateen.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class SingleInputDialog private constructor(private val dialog: AlertDialog) {

    fun show() {
        dialog.show()
    }

    /**************************************************************************
    ** Builder Pattern
    **************************************************************************/
    class Builder(private val context: Context) {

        private var title: String = ""
        private var inputHint: String = "Input"
        private var inputValue: String = ""
        private var positiveButtonText: String = "OK"
        private var negativeButtonText: String = "CANCEL"

        private var cancelable: Boolean = false
        private var required: Boolean = true

        private lateinit var dialogBuilder: AlertDialog.Builder
        private lateinit var dialog: AlertDialog

        private var submitListener: ((text: String) -> Unit)? = null
        private var cancelListener: (() -> Unit)? = null

        fun title(title: String) = apply { this.title = title }
        fun inputHint(hint: String) = apply { this.inputHint = hint }
        fun inputValue(value: String) = apply { this.inputValue = value }
        fun cancelable(cancelAble: Boolean) = apply { this.cancelable = cancelAble }
        fun required(required: Boolean) = apply { this.required = required }

        fun positiveButton(text: String, listener: (text: String) -> Unit) =
            apply { this.positiveButtonText = text; this.submitListener = listener }

        fun negativeButton(text: String, listener: () -> Unit) =
                apply { this.negativeButtonText = text; this.cancelListener = listener }

        fun show(): SingleInputDialog {
            initDialog()
            dialog.show()
            return SingleInputDialog(dialog)
        }

        fun build(): SingleInputDialog {
            initDialog()
            return SingleInputDialog(dialog)
        }

        private fun initDialog() {
            dialogBuilder = AlertDialog.Builder(context)

            val view = LayoutInflater.from(context).inflate(R.layout.dialog_single_input, null)

            val layoutInputField1 = view.findViewById<TextInputLayout>(R.id.text_input_layout1)
            val inputField1 = view.findViewById<EditText>(R.id.text_field1)

            inputField1.hint = inputHint
            if(inputValue.isNotEmpty()) inputField1.setText(inputValue)

            dialogBuilder.setTitle(title).setCancelable(cancelable).setView(view)

            dialogBuilder.setPositiveButton(positiveButtonText) { dialog, _ ->
                if(required) {
                    val text = inputField1.text.trim().toString()
                    if(text.isEmpty()) {
                        layoutInputField1.error = "Required"
                    } else {
                        layoutInputField1.isErrorEnabled = false
                        submitListener?.invoke(inputField1.text.trim().toString())
                        dialog.dismiss()
                    }
                }
            }

            dialogBuilder.setNegativeButton(negativeButtonText) { dialog, _ ->
                cancelListener?.invoke()
                dialog.dismiss()
            }

            dialog = dialogBuilder.create()
        }
    }
}