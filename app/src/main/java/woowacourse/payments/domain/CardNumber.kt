package woowacourse.payments.domain

import androidx.core.text.isDigitsOnly

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    sealed class CardNumberError(
        message: String,
    ) : Exception(message) {
        class ExceedsLength : CardNumberError("카드 번호는 정확히 ${VALID_CARD_NUMBER_LENGTH}자리여야 합니다. (입력값이 너무 깁니다)")

        class InsufficientLength : CardNumberError("카드 번호는 정확히 ${VALID_CARD_NUMBER_LENGTH}자리여야 합니다. (입력값이 너무 짧습니다)")

        class NonDigit : CardNumberError("카드 번호는 숫자만 입력할 수 있습니다.")
    }

    companion object {
        private const val VALID_CARD_NUMBER_LENGTH = 16

        fun from(value: String): CardNumber {
            if (!value.isDigitsOnly()) throw CardNumberError.NonDigit()
            if (value.length < VALID_CARD_NUMBER_LENGTH) throw CardNumberError.InsufficientLength()
            if (value.length > VALID_CARD_NUMBER_LENGTH) throw CardNumberError.ExceedsLength()

            return CardNumber(value)
        }
    }
}
