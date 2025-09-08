package woowacourse.payments.domain

@JvmInline
value class CardNumber(
    val numbers: List<CardDigit>,
) {
    init {
        require(numbers.size == CARD_NUMBER_LENGTH) { ERROR_INVALID_CARD_NUMBER_LENGTH }
    }

    companion object {
        const val CARD_NUMBER_LENGTH = 16
        private const val ERROR_INVALID_CARD_NUMBER_LENGTH = "카드 번호는 16자리여야 합니다."
        private const val ERROR_INVALID_CARD_NUMBER_FORMAT = "카드 번호는 숫자로만 이루어져야 합니다."

        fun from(numbers: String): CardNumber {
            require(numbers.all(Char::isDigit)) { ERROR_INVALID_CARD_NUMBER_FORMAT }
            return CardNumber(numbers.map { CardDigit(it.digitToInt()) })
        }
    }
}
