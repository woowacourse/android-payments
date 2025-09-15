package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidCardException

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    companion object {
        private const val CARD_NUMBER_LENGTH = 16

        fun from(raw: String): CardNumber? = if (raw.length == CARD_NUMBER_LENGTH) CardNumber(raw) else null

        fun require(raw: String): CardNumber = from(raw) ?: throw InvalidCardException.InvalidCardNumber()
    }
}
