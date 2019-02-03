package com.arbazmateen.dialogs

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout


/**************************************************************************
** Single input dialog
**************************************************************************/
class SingleInputDialog private constructor(private val dialog: AlertDialog) {

    fun show() {
        dialog.show()
    }

    /**************************************************************************
    ** Builder Pattern
    **************************************************************************/
    class Builder(private val context: Context) {

        private var title = ""
        private var inputHint = "Input"
        private var inputValue = ""
        private var positiveButtonText = "OK"
        private var negativeButtonText = "CANCEL"

        private lateinit var layoutInputField1: TextInputLayout
        private lateinit var inputField1: EditText

        private var cancelable = false
        private var required = true
        private var validate = false

        private var regex = ""
        private var errorMsg = ""

        private var multiLine = false
        private var minLength = 3
        private var maxLength = 4000

        private lateinit var dialogBuilder: AlertDialog.Builder
        private lateinit var dialog: AlertDialog

        private var submitListener: ((text: String) -> Unit)? = null
        private var cancelListener: (() -> Unit)? = null

        fun title(title: String) = apply { this.title = title }
        fun inputHint(hint: String) = apply { this.inputHint = hint }
        fun inputValue(value: String) = apply { this.inputValue = value }
        fun cancelable(cancelAble: Boolean) = apply { this.cancelable = cancelAble }
        fun required(required: Boolean, minLength: Int = 3, maxLength: Int = 4000) =
            apply { this.required = required; this.minLength = minLength; this.maxLength = maxLength }
        fun validate(regex: String, errorMsg: String) =
            apply { validate = true; this.regex = regex; this.errorMsg = errorMsg }

        fun multiLine(multiLine: Boolean) = apply { this.multiLine = multiLine }

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

            layoutInputField1 = view.findViewById(R.id.text_input_layout1)
            inputField1 = view.findViewById(R.id.text_field1)

            inputField1.hint = inputHint
            if(inputValue.isNotEmpty()) inputField1.setText(inputValue)

            with(inputField1) {
                if(multiLine) {
                    setSingleLine(false)
                    inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    minLines = 3
                    maxLines = 5
                    imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
                } else {
                    setSingleLine(true)
                    inputType = InputType.TYPE_CLASS_TEXT
                    setOnKeyListener { _, keyCode, keyEvent ->
                        if(keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                            if(keyEvent.action == KeyEvent.ACTION_DOWN) {
                                if(checkInput()) {
                                    submitListener?.invoke(inputField1.text.trim().toString())
                                    dialog.dismiss()
                                }
                                return@setOnKeyListener true
                            }
                        }
                        return@setOnKeyListener false
                    }
                }
            }

            dialogBuilder.setTitle(title).setCancelable(cancelable).setView(view)

            dialogBuilder.setPositiveButton(positiveButtonText) { dialog, _ ->
                if(checkInput()) {
                    submitListener?.invoke(inputField1.text.trim().toString())
                    dialog.dismiss()
                }
            }

            dialogBuilder.setNegativeButton(negativeButtonText) { dialog, _ ->
                cancelListener?.invoke()
                dialog.dismiss()
            }

            dialog = dialogBuilder.create()
        }

        private fun checkInput(): Boolean {
            val text = inputField1.text.trim().toString()
            return when {
                required && validate -> {
                    when {
                        text.isEmpty() -> {
                            layoutInputField1.error = "Required"
                            false
                        }
                        text.length < minLength -> {
                            layoutInputField1.error = "Minimum length required: $minLength"
                            false
                        }
                        text.length > maxLength -> {
                            layoutInputField1.error = "Maximum length allowed: $maxLength"
                            false
                        }
                        !text.matches(Regex(regex)) -> {
                            layoutInputField1.error = errorMsg
                            false
                        }
                        else -> {
                            layoutInputField1.isErrorEnabled = false
                            true
                        }
                    }
                }
                required -> {
                    when {
                        text.isEmpty() -> {
                            layoutInputField1.error = "Required"
                            false
                        }
                        text.length < minLength -> {
                            layoutInputField1.error = "Minimum length required: $minLength"
                            false
                        }
                        text.length > maxLength -> {
                            layoutInputField1.error = "Maximum length allowed: $maxLength"
                            false
                        }
                        else -> {
                            layoutInputField1.isErrorEnabled = false
                            true
                        }
                    }
                }
                validate -> {
                    if(!text.matches(Regex(regex))){
                        layoutInputField1.error = errorMsg
                        false
                    } else {
                        layoutInputField1.isErrorEnabled = false
                        true
                    }
                }
                else -> {
                    true
                }
            }
        }
    }
}

/**************************************************************************
** Custom layout dialog
**************************************************************************/
class CustomLayoutDialog private constructor(private val layout: Int,
                                             private val views: Map<Int, View>,
                                             private val dialog: AlertDialog) {

    fun show() {
        dialog.show()
    }

    /**************************************************************************
    ** Builder pattern
    **************************************************************************/
    class Builder(private val context: Context) {



    }

}