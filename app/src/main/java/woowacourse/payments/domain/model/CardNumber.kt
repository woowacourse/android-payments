package woowacourse.payments.domain.model

import woowacourse.payments.domain.validator.ValidationErrorType

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    companion object {
        const val CARD_NUMBER_LENGTH = 16

        fun create(value: String): CardNumber {
            require(value.length == CARD_NUMBER_LENGTH) { "카드 번호는 16자리여야 합니다." }
            require(value.all(Char::isDigit)) { "카드 번호는 숫자로만 구성되어야 합니다." }
            return CardNumber(value)
        }

        fun validationErrorType(raw: String): ValidationErrorType? =
            when {
                !raw.all(Char::isDigit) -> ValidationErrorType.InvalidFormat
                raw.length > CARD_NUMBER_LENGTH -> ValidationErrorType.InvalidCardNumberLength
                raw.length < CARD_NUMBER_LENGTH -> ValidationErrorType.InvalidCardNumberLength
                else -> null
            }
    }
}
