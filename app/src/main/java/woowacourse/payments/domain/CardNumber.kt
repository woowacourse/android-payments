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
    }
}
