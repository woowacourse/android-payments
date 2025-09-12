package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidCardNumberException

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    companion object {
        fun from(raw: String): CardNumber? {
            val digits = raw.filter(Char::isDigit)
            return if (digits.length == 16) CardNumber(digits) else null
        }

        fun require(raw: String): CardNumber = from(raw) ?: throw InvalidCardNumberException()
    }
}
