package com.arbazmateen.validator

import android.widget.EditText

/**************************************************************************
** Extension functions
**************************************************************************/
fun EditText.validator() = InputValidator(this.text.toString().trim())

/**************************************************************************
** Edit text validator class
**************************************************************************/

class InputValidator(val text: String) {

    private var isValid = true
    private var isOptional = false

    private var minimumLength = 3
    private var maximumLength = 500

    private var errorMessage = ""

    private var successListener: (() -> Unit)? = null
    private var errorListener: ((error: String) -> Unit)? = null

    fun validate(): Boolean {
        if(isValid) {
            successListener?.invoke()
        } else {
            errorListener?.invoke(errorMessage)
        }

        return isValid
    }

    fun required(): InputValidator {
        if(text.isEmpty()) {
            setErrorMessage("Required field.")
        }
        return this
    }

    fun required(regex: String, error: String = "Invalid input"): InputValidator {
        if(!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun allow(regex: String, error: String = "Invalid input"): InputValidator {
        if(isOptional()) return this
        if(!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun optional(): InputValidator {
        isOptional = true
        minimumLength = 0
        return this
    }

    fun minimumLength(length: Int): InputValidator {
        minimumLength = length
        if(isOptional()) return this
        if(text.length < minimumLength) {
            setErrorMessage("Minimum length required: $minimumLength")
        }
        return this
    }

    fun maximumLength(length: Int): InputValidator {
        maximumLength = length
        if(text.length > maximumLength) {
            setErrorMessage("Maximum length allowed: $maximumLength")
        }
        return this
    }

    fun setSuccessListener(successListener: () -> Unit): InputValidator {
        this.successListener = successListener
        return this
    }

    fun setErrorListener(errorListener: (error: String) -> Unit): InputValidator {
        this.errorListener = errorListener
        return this
    }

    private fun isOptional(): Boolean {
        return isOptional && text.isEmpty()
    }

    private fun setErrorMessage(errorMsg: String) {
        isValid = false
        errorMessage += "$errorMsg\n"
    }
}