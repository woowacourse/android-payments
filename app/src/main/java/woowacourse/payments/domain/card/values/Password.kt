package woowacourse.payments.domain.card.values

import woowacourse.payments.domain.card.exception.PasswordException

@JvmInline
value class Password private constructor(
    val value: String,
) {
    init {
        if (value.length != MAX_LENGTH_PASSWORD) {
            throw PasswordException.InvalidLength
        }
        if (!value.all(Char::isDigit)) {
            throw PasswordException.NotDigit
        }
    }

    override fun toString(): String = "****"

    companion object {
        const val MAX_LENGTH_PASSWORD = 4

        fun create(value: String): Result<Password> =
            runCatching {
                Password(value)
            }
    }
}
