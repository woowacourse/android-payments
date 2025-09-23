package woowacourse.payments.domain.model

import woowacourse.payments.domain.validator.ValidationErrorType

@JvmInline
value class Password private constructor(
    val value: String,
) {
    companion object {
        const val PASSWORD_LENGTH = 4

        fun create(value: String): Password {
            require(value.length == PASSWORD_LENGTH) { "비밀번호는 ${PASSWORD_LENGTH}자리여야 합니다." }
            require(value.all(Char::isDigit)) { "비밀번호는 숫자로만 구성되어야 합니다." }
            return Password(value)
        }

        fun validationErrorType(raw: String): ValidationErrorType? =
            when {
                !raw.all(Char::isDigit) -> ValidationErrorType.InvalidCharacters
                raw.length > PASSWORD_LENGTH -> ValidationErrorType.InvalidPasswordLength
                raw.length < PASSWORD_LENGTH -> ValidationErrorType.InvalidPasswordLength
                else -> null
            }
    }
}
