package woowacourse.payments.domain

@JvmInline
value class CardNumber(val value: List<Digit>) {
    constructor(value: String) : this(value.map { Digit(it.digitToInt()) })

    init {
        require(value.size == CARD_NUMBER_LENGTH) { "카드 번호는 ${CARD_NUMBER_LENGTH}자리여야 합니다."}
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
    }
}