package woowacourse.payments.domain.model

import woowacourse.payments.domain.exception.InvalidCardException

@JvmInline
value class Pin private constructor(
    val value: String,
) {
    companion object {
        private const val PIN_LENGTH = 4

        fun from(raw: String): Pin? = if (raw.length == PIN_LENGTH) Pin(raw) else null

        fun require(raw: String): Pin = from(raw) ?: throw InvalidCardException.InvalidPin()
    }
}
