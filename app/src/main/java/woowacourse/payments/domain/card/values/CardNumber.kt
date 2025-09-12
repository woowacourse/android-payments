package woowacourse.payments.domain.card.values

import woowacourse.payments.domain.card.exception.CardNumberException

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    init {
        if (value.length != MAX_LENGTH_CARD_NUMBER) {
            throw CardNumberException.InvalidLength
        }
        if (!value.all(Char::isDigit)) {
            throw CardNumberException.NotDigit
        }
    }

    override fun toString(): String = "카드번호의 마지막 4자리: ${value.takeLast(4)}"

    companion object {
        const val MAX_LENGTH_CARD_NUMBER = 16

        fun create(value: String): Result<CardNumber> =
            runCatching {
                CardNumber(value)
            }
    }
}
