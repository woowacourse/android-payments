package woowacourse.payments.domain

import androidx.core.text.isDigitsOnly

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    sealed class CardNumberException(
        message: String,
    ) : Exception(message) {
        class InvalidLengthException(
            val kind: Kind,
        ) : CardNumberException("카드 번호는 ${VALID_CARD_NUMBER_LENGTH}자리여야 합니다.") {
            enum class Kind { INSUFFICIENT, EXCEEDS }
        }

        class NonDigitException : CardNumberException("카드 번호는 숫자만 입력할 수 있습니다.")
    }

    companion object {
        private const val VALID_CARD_NUMBER_LENGTH = 16

        fun from(value: String): CardNumber {
            if (!value.isDigitsOnly()) throw CardNumberException.NonDigitException()
            if (value.length < VALID_CARD_NUMBER_LENGTH) {
                throw CardNumberException.InvalidLengthException(CardNumberException.InvalidLengthException.Kind.INSUFFICIENT)
            }
            if (value.length > VALID_CARD_NUMBER_LENGTH) {
                throw CardNumberException.InvalidLengthException(CardNumberException.InvalidLengthException.Kind.EXCEEDS)
            }

            return CardNumber(value)
        }
    }
}
