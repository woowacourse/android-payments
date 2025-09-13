@file:Suppress("ktlint:standard:filename")

package woowacourse.payments.domain.validator

sealed class ValidationErrorType {
    object InvalidFormat : ValidationErrorType()

    object ExpiredDate : ValidationErrorType()

    object InvalidCharacters : ValidationErrorType()

    object InvalidCardNumberLength : ValidationErrorType()

    object InvalidUserNameLength : ValidationErrorType()

    object InvalidPasswordLength : ValidationErrorType()
}
