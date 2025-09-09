package woowacourse.payments.domain.validator

class PasswordValidator {
    fun validate(input: String): ValidationResult =
        if (input.length == PASSWORD_MAX_LENGTH) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(ValidationErrorType.InvalidLength(FieldType.PASSWORD))
        }

    companion object {
        private const val PASSWORD_MAX_LENGTH = 4
    }
}
