package woowacourse.payments.domain.validator

class CardNumberValidator {
    fun validate(input: String): ValidationResult =
        if (input.length == CARD_NUMBER_MAX_LENGTH) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(ValidationErrorType.InvalidLength(FieldType.CARD_NUMBER))
        }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH = 16
    }
}
