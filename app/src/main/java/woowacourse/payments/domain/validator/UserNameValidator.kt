package woowacourse.payments.domain.validator

class UserNameValidator {
    fun validate(input: String): ValidationResult {
        val pattern = Regex("^[가-힣a-zA-Z\\s]*$")

        if (input.length > USER_NAME_MAX_LENGTH) {
            return ValidationResult.Error(
                ValidationErrorType.InvalidLength(FieldType.USER_NAME),
            )
        }

        if (!input.matches(pattern)) {
            return ValidationResult.Error(ValidationErrorType.InvalidCharacters)
        }

        return ValidationResult.Success
    }

    companion object {
        private const val USER_NAME_MAX_LENGTH = 30
    }
}
