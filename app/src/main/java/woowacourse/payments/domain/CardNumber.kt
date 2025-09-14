package woowacourse.payments.domain

data class CardNumber(
    val value: String,
) {
    init {
        require(value.all(Char::isDigit)) { NON_NUMERIC_CHARACTER_ERROR_MESSAGE }
        require(value.length == CARD_NUMBER_LENGTH) { REQUIRED_LENGTH_ERROR_MESSAGE }
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
        private const val NON_NUMERIC_CHARACTER_ERROR_MESSAGE = "카드 번호는 숫자로만 이루어질 수 있습니다."
        private const val REQUIRED_LENGTH_ERROR_MESSAGE = "카드 번호는 ${CARD_NUMBER_LENGTH}자여야 합니다."
    }
}
