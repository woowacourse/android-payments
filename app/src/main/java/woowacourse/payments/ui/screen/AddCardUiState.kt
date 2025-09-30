package woowacourse.payments.ui.screen

import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.domain.validator.ValidationErrorType

data class AddCardUiState(
    val number: String = "",
    val expiration: String = "",
    val userName: String = "",
    val password: String = "",
    val numberError: ValidationErrorType? = null,
    val expirationError: ValidationErrorType? = null,
    val userNameError: ValidationErrorType? = null,
    val passwordError: ValidationErrorType? = null,
    val selectedCompany: CardCompanyType = CardCompanyType.NOT_SELECTED,
    val showCompanySheet: Boolean = true,
)
