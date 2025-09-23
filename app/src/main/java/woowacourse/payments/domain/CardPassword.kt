package woowacourse.payments.domain

import woowacourse.payments.domain.extension.isDigitsOnly

@JvmInline
value class CardPassword private constructor(
    val value: String,
) {
    sealed class CardPasswordException(
        message: String,
    ) : Exception(message) {
        data class InvalidLengthException(
            val kind: Kind,
        ) : CardPasswordException("카드 비밀번호는 ${VALID_CARD_PASSWORD_LENGTH}자리여야 합니다.") {
            enum class Kind { INSUFFICIENT, EXCEEDS }
        }

        data object NonDigitException : CardPasswordException("카드 비밀번호는 숫자만 입력할 수 있습니다.") {
            private fun readResolve(): Any = NonDigitException
        }
    }

    companion object {
        private const val VALID_CARD_PASSWORD_LENGTH = 4

        fun from(value: String): CardPassword {
            if (!value.isDigitsOnly()) throw CardPasswordException.NonDigitException
            if (value.length < VALID_CARD_PASSWORD_LENGTH) {
                throw CardPasswordException.InvalidLengthException(CardPasswordException.InvalidLengthException.Kind.INSUFFICIENT)
            }
            if (value.length > VALID_CARD_PASSWORD_LENGTH) {
                throw CardPasswordException.InvalidLengthException(CardPasswordException.InvalidLengthException.Kind.EXCEEDS)
            }

            return CardPassword(value)
        }
    }
}
