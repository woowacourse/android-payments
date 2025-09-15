package woowacourse.payments.domain.model

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    init {
        require(value.length == CARD_NUMBER_LENGTH) { "카드 번호는 16자리여야 합니다." }
        require(value.all(Char::isDigit)) { "카드 번호는 숫자로만 구성되어야 합니다." }
    }

    companion object {
        const val CARD_NUMBER_LENGTH = 16

        fun from(value: String): CardNumber = CardNumber(value)
    }
}
