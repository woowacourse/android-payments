package woowacourse.payments.domain.validator

enum class FieldType {
    CARD_NUMBER,
    EXPIRATION_DATE,
    USER_NAME,
    PASSWORD,
}

sealed class ValidationErrorType {
    object InvalidFormat : ValidationErrorType()

    object ExpiredDate : ValidationErrorType()

    object InvalidCharacters : ValidationErrorType()

    data class InvalidLength(
        val fieldType: FieldType,
    ) : ValidationErrorType()
}

sealed class ValidationResult {
    object Success : ValidationResult()

    data class Error(
        val type: ValidationErrorType,
    ) : ValidationResult()
}
