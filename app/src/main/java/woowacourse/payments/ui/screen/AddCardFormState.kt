package woowacourse.payments.ui.screen

import woowacourse.payments.domain.validator.ValidationErrorType

data class AddCardFormState(
    val number: String = "",
    val expiration: String = "",
    val userName: String = "",
    val password: String = "",
    val numberErrorType: ValidationErrorType? = null,
    val expirationErrorType: ValidationErrorType? = null,
    val userNameErrorType: ValidationErrorType? = null,
    val passwordErrorType: ValidationErrorType? = null,
    val isSaveEnabled: Boolean = false,
)
