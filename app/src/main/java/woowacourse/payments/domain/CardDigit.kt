package woowacourse.payments.domain

@JvmInline
value class CardDigit(
    val value: Int,
) {
    init {
        require(value in MIN_CARD_DIGIT..MAX_CARD_DIGIT) { ERROR_INVALID_CARD_DIGIT }
    }

    companion object {
        private const val MIN_CARD_DIGIT = 0
        private const val MAX_CARD_DIGIT = 9
        private const val ERROR_INVALID_CARD_DIGIT = "카드 번호의 각 자리는 0~9 사이여야 합니다."
    }
}
