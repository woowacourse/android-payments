package woowacourse.payments.domain.validator

class CardNumberValidator {
    fun validate(input: String): ValidationResult =
        when {
            input.length != CARD_NUMBER_LENGTH ->
                ValidationResult.Error(ValidationErrorType.InvalidLength(FieldType.CARD_NUMBER))

            !input.all(Char::isDigit) ->
                ValidationResult.Error(ValidationErrorType.InvalidFormat)

            else -> ValidationResult.Success
        }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
    }
}
