package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidPinException

@JvmInline
value class Pin private constructor(
    val value: String,
) {
    companion object {
        fun from(raw: String): Pin? {
            val digits = raw.filter(Char::isDigit)
            return if (digits.length == 4) Pin(digits) else null
        }

        fun require(raw: String): Pin = from(raw) ?: throw InvalidPinException()
    }
}
