package com.arbazmateen.dialogs

data class DataModel(override var itemId: Long, override var displayText: String, override var isSelected: Boolean):
    MultiSelectModel(itemId, displayText, isSelected) {

    override fun toString(): String {
        return displayText
    }
}