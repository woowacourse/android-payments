package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidCardException

@JvmInline
value class Expiry private constructor(
    val value: String,
) {
    companion object {
        private const val EXPIRY_LENGTH = 4
        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val MIN_YEAR = 25
        private const val MAX_YEAR = 99

        fun from(raw: String): Expiry? {
            if (raw.length != EXPIRY_LENGTH) return null
            val month = raw.take(2).toIntOrNull() ?: return null
            val year = raw.takeLast(2).toIntOrNull() ?: return null
            if (month !in MIN_MONTH..MAX_MONTH || year !in MIN_YEAR..MAX_YEAR) return null
            return Expiry(raw)
        }

        fun require(raw: String): Expiry = from(raw) ?: throw InvalidCardException.InvalidExpiry()
    }
}
