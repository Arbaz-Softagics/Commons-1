package com.arbazmateen.validator

import android.util.Patterns
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

    private var validListener: (() -> Unit)? = null
    private var invalidListener: ((error: String) -> Unit)? = null

    fun validate(): Boolean {
        if (isValid) {
            validListener?.invoke()
        } else {
            invalidListener?.invoke(errorMessage)
        }

        return isValid
    }

    fun required(): InputValidator {
        if (text.isEmpty()) {
            setErrorMessage("Required field.")
        }
        return this
    }

    fun required(regex: String, error: String = "Invalid input"): InputValidator {
        if (!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun allow(regex: String, error: String = "Invalid input"): InputValidator {
        if (isOptional()) return this
        if (!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun optional(): InputValidator {
        isOptional = true
        minimumLength = 0
        return this
    }

    fun optional(regex: String): InputValidator {
        isOptional = true
        minimumLength = 0
        allow(regex)
        return this
    }

    fun validateEmail(builtInCheck: Boolean = false): InputValidator {
        if (isOptional()) return this
        if (builtInCheck && !(text.matches(Regex(REGEX_EMAIL)) || Patterns.EMAIL_ADDRESS.matcher(text).matches())) {
            setErrorMessage("Invalid email address.")
        } else if (!text.matches(Regex(REGEX_EMAIL))) {
            setErrorMessage("Invalid email address.")
        }
        return this
    }

    fun validatePassword(allowSpace: Boolean = true): InputValidator {
        if (isOptional()) return this
        val regex = if (allowSpace) {
            REGEX_PASSWORD_CUSTOM_LENGTH = "$minimumLength,$maximumLength"
            REGEX_PASSWORD_CUSTOM_LENGTH
        } else {
            REGEX_PASSWORD_WO_SPACE_CUSTOM_LENGTH = "$minimumLength,$maximumLength"
            REGEX_PASSWORD_WO_SPACE_CUSTOM_LENGTH
        }
        if (!text.matches(Regex(regex))) {
            var e = "Must contains:\n Upper Letters,\n Lower Letters,\n Numbers," +
                    "\n Special Characters,\n Min Length: $minimumLength,\n Max Length: $maximumLength"
            if (!allowSpace) e += ",\n Space not allowed"
            setErrorMessage(e)
        }
        return this
    }

    fun validateName(allowSpace: Boolean = false,
                     allowHyphen: Boolean = false,
                     allowDot: Boolean = false,
                     allowUnderscore: Boolean = false): InputValidator {
        if (isOptional()) return this
        var error = "Invalid name:\n Only characters allowed"
        val regex = when {
            allowSpace && allowHyphen && allowDot && allowUnderscore -> {
                error = "Invalid name:\n Only characters, Space, (-), (.), (_) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT__
            }
            allowSpace && allowHyphen && allowDot -> {
                error = "Invalid name:\n Only characters, Space, (-), (.) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT
            }
            allowSpace && allowHyphen -> {
                error = "Invalid name:\n Only characters, Space, (-) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN
            }
            allowSpace -> {
                error = "Invalid name:\n Only characters, Space allowed"
                REGEX_CHARACTERS_W_SPACE
            }
            else -> {
                REGEX_CHARACTERS
            }
        }
        if (!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun validateText(allowNumbers: Boolean = false,
                     allowSpace: Boolean = false,
                     allowHyphen: Boolean = false,
                     allowDot: Boolean = false,
                     allowUnderscore: Boolean = false): InputValidator {
        if (isOptional()) return this
        var error = "Invalid input:\n Only characters allowed"
        val regex = when {
            allowNumbers -> {
                when {
                    allowSpace && allowHyphen && allowDot && allowUnderscore -> {
                        error = "Invalid input:\n Only numbers, characters,\n Space, (-), (.) & (_) allowed"
                        REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN_DOT__
                    }
                    allowSpace && allowHyphen && allowDot -> {
                        error = "Invalid input:\n Only numbers, characters,\n Space, (-) & (.) allowed"
                        REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN_DOT
                    }
                    allowSpace && allowHyphen -> {
                        error = "Invalid input:\n Only numbers, characters,\n Space & (-) allowed"
                        REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN
                    }
                    allowSpace -> {
                        error = "Invalid input:\n Only numbers, characters & Space allowed"
                        REGEX_ALPHA_NUMERIC_W_SPACE
                    }
                    else -> {
                        error = "Invalid input:\n Only numbers & characters allowed"
                        REGEX_ALPHA_NUMERIC
                    }
                }
            }
            allowSpace && allowHyphen && allowDot && allowUnderscore -> {
                error = "Invalid input:\n Only characters, Space,\n (-), (.) & (_) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT__
            }
            allowSpace && allowHyphen && allowDot -> {
                error = "Invalid input:\n Only characters, Space,\n (-) & (.) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT
            }
            allowSpace && allowHyphen -> {
                error = "Invalid input:\n Only characters, Space & (-) allowed"
                REGEX_CHARACTERS_W_SPACE_HYPHEN
            }
            allowSpace -> {
                error = "Invalid input:\n Only characters & Space allowed"
                REGEX_CHARACTERS_W_SPACE
            }
            else -> {
                REGEX_CHARACTERS
            }
        }
        if (!text.matches(Regex(regex))) {
            setErrorMessage(error)
        }
        return this
    }

    fun validateNumbers(allowNegative: Boolean = false, minValue: Int = Int.MIN_VALUE, maxValue: Int = Int.MAX_VALUE): InputValidator {
        if (isOptional()) return this
        val regex = if (allowNegative) REGEX_NUMBERS else REGEX_POSITIVE_NUMBERS
        if(!text.matches(Regex(regex))) {
            setErrorMessage("Only numbers allowed")
        } else if(text.toInt() < minValue) {
            setErrorMessage("Minimum value allowed: $minValue")
        } else if(text.toInt() > maxValue) {
            setErrorMessage("Maximum value allowed: $maxValue")
        }
        return this
    }

    fun validateFloatingPointNumbers(allowNegative: Boolean = false): InputValidator {
        if (isOptional()) return this
        val regex = if (allowNegative) REGEX_TWO_FLOATING_POINT else REGEX_POSITIVE_TWO_FLOATING_POINT
        if(!text.matches(Regex(regex))) {
            setErrorMessage("Only floating point numbers with 2 decimal allowed")
        }
        return this
    }

    fun minimumLength(length: Int): InputValidator {
        minimumLength = length
        if (isOptional()) return this
        if (text.length < minimumLength) {
            setErrorMessage("Minimum length required: $minimumLength")
        }
        return this
    }

    fun maximumLength(length: Int): InputValidator {
        maximumLength = length
        if (text.length > maximumLength) {
            setErrorMessage("Maximum length allowed: $maximumLength")
        }
        return this
    }

    fun setValidListener(successListener: () -> Unit): InputValidator {
        this.validListener = successListener
        return this
    }

    fun setInvalidListener(errorListener: (error: String) -> Unit): InputValidator {
        this.invalidListener = errorListener
        return this
    }

    fun setCustomErrorMessage(error: String): InputValidator {
        errorMessage = error
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