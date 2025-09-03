package woowacourse.payments.domain

import androidx.core.text.isDigitsOnly

data class CardNumber(
    val value: String,
) {
    init {
        require(value.all(Char::isDigit)) { IllegalArgumentException() }
        require(value.length == CARD_NUMBER_LENGTH) { IllegalArgumentException() }
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
    }
}
