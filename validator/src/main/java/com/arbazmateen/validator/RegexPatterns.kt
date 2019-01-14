package com.arbazmateen.validator

const val REGEX_EMAIL =
    "^[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{2,256}\\@[a-zA-Z0-9\\-]{2,64}(\\.[a-zA-Z\\-]{2,25})*\\.[a-zA-Z\\-]{2,25}\$"

const val REGEX_PASSWORD =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@!~%_\\-+=`',/:;\\\\< >{|}#\\^*?&()\\]\\[.\"]).{6,}\$"

const val REGEX_PASSWORD_WO_SPACE =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@!~%_\\-+=`',/:;\\\\<>{|}#\\^*?&()\\]\\[.\"])(?=\\S+\$).{6,}\$"

var REGEX_PASSWORD_CUSTOM_LENGTH: String = REGEX_PASSWORD
    set(length) {
        field.replace("{6,}", "{$length}")
    }

var REGEX_PASSWORD_WO_SPACE_CUSTOM_LENGTH: String = REGEX_PASSWORD_WO_SPACE
    set(length) {
        field.replace("{6,}", "{$length}")
    }

const val REGEX_LOWER_CASE_CHARACTERS = "^[a-z]+\$"
const val REGEX_UPPER_CASE_CHARACTERS = "^[A-Z]+\$"
const val REGEX_CHARACTERS = "^[a-zA-Z]+\$"
const val REGEX_CHARACTERS_W_SPACE = "^[a-zA-Z ]+\$"
const val REGEX_CHARACTERS_W_SPACE_HYPHEN = "^[a-zA-Z -]+\$"
const val REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT = "^[a-zA-Z -.]+\$"
const val REGEX_CHARACTERS_W_SPACE_HYPHEN_DOT__ = "^[a-zA-Z -._]+\$"

const val REGEX_ALPHA_NUMERIC = "^[a-zA-Z0-9]+\$"
const val REGEX_ALPHA_NUMERIC_W_SPACE = "^[a-zA-Z0-9 ]+\$"
const val REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN = "^[a-zA-Z0-9 -]+\$"
const val REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN_DOT = "^[a-zA-Z0-9 -.]+\$"
const val REGEX_ALPHA_NUMERIC_W_SPACE_HYPHEN_DOT__ = "^[a-zA-Z0-9 -._]+\$"

const val REGEX_ANY_NUMBER = "^-?\\d+(\\.\\d+)?\$"
const val REGEX_ANY_POSITIVE_NUMBER = "^\\d+(\\.\\d+)?\$"
const val REGEX_NUMBERS = "^-?[1-9]+[0-9]*\$|^[0-9]+\$"
const val REGEX_POSITIVE_NUMBERS = "^0\$|^[1-9]\\d*\$"
const val REGEX_NEGATIVE_NUMBERS = "^-[1-9]\\d*\$"

const val REGEX_PAK_LL_NUMBER = "^0\\d{2}[- ]?\\d{7}\$|^\\d{10}\$"
const val REGEX_PAK_CELL_NUMBER = "^((\\+92)|(0092))[- ]?3\\d{2}[- ]?\\d{7}\$|^03\\d{2}[- ]?\\d{7}\$"

const val REGEX_PAK_CNIC = "^\\d{5}[- ]?\\d{7}[- ]?\\d\$"
const val REGEX_PAK_NTN = "^\\d{7}[- ]?\\d\$"
const val REGEX_PAK_STN = "^\\d{2}[- ]?\\d{2}[- ]?\\d{4}[- ]?\\d{3}[- ]?\\d{2}[- ]?\$"

const val REGEX_IBAN = "^[a-zA-Z]{2}\\d{2} ?[a-zA-Z]{4} ?\\d{4} ?\\d{4} ?\\d{4} ?\\d{4}\$"

const val REGEX_TWO_FLOATING_POINT =
    "^-?0\\.([1-9][0-9]|[0-9][1-9])\$|^-?[1-9]*[0-9]*(\\.[0-9]{0,2})?\$"

const val REGEX_THREE_FLOATING_POINT =
    "^-?0\\.([1-9][0-9][0-9]|[0-9][1-9][0-9]|[0-9][0-9][1-9])\$|^-?[1-9]*[0-9]*(\\.[0-9]{0,2})?\$"

const val REGEX_POSITIVE_TWO_FLOATING_POINT =
    "^0\$|^0\\.([1-9][0-9]|[0-9][1-9])\$|^[1-9]*[0-9]*(\\.[0-9]{0,2})?\$"

const val REGEX_POSITIVE_THREE_FLOATING_POINT =
    "^0\$|^0\\.([1-9][0-9][0-9]|[0-9][1-9][0-9]|[0-9][0-9][1-9])\$|^[1-9]*[0-9]*(\\.[0-9]{0,3})?\$"

object Allow {
    const val NUMBERS = "0-9"
    const val UPPERCASE = "A-Z"
    const val LOWERCASE = "a-z"
    const val SPACE = " "
    const val HYPHEN = "-"
    const val UNDERSCORE = "_"
    const val DOT = "."
    const val AT = "@"
    const val SPECIAL_CHARS = "`~!@#\$%\\^&*()_\\-+={\\[}\\]|\\\\:;\"'<,>.?/"
}
