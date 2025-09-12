package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidExpiryException

@JvmInline
value class Expiry private constructor(
    val value: String,
) {
    companion object {
        fun from(raw: String): Expiry? {
            val digits = raw.filter(Char::isDigit)
            if (digits.length != 4) return null
            val month = digits.take(2).toIntOrNull() ?: return null
            val year = digits.takeLast(2).toIntOrNull() ?: return null
            if (month !in 1..12 || year !in 25..99) return null
            return Expiry(digits)
        }

        fun require(raw: String): Expiry = from(raw) ?: throw InvalidExpiryException()
    }
}
