package woowacourse.payments.domain

import androidx.core.text.isDigitsOnly

@JvmInline
value class CardPassword private constructor(
    val value: String,
) {
    sealed class CardPasswordError(
        message: String,
    ) : Exception(message) {
        class ExceedsLength : CardPasswordError("카드 비밀번호는 정확히 ${VALID_CARD_PASSWORD_LENGTH}자리여야 합니다. (입력값이 너무 깁니다)")

        class InsufficientLength : CardPasswordError("카드 비밀번호는 정확히 ${VALID_CARD_PASSWORD_LENGTH}자리여야 합니다. (입력값이 너무 짧습니다)")

        class NonDigit : CardPasswordError("카드 비밀번호는 숫자만 입력할 수 있습니다.")
    }

    companion object {
        private const val VALID_CARD_PASSWORD_LENGTH = 4

        fun from(value: String): CardPassword {
            if (!value.isDigitsOnly()) throw CardPasswordError.NonDigit()
            if (value.length < VALID_CARD_PASSWORD_LENGTH) throw CardPasswordError.InsufficientLength()
            if (value.length > VALID_CARD_PASSWORD_LENGTH) throw CardPasswordError.ExceedsLength()

            return CardPassword(value)
        }
    }
}
