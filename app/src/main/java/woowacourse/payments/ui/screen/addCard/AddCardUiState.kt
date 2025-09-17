package woowacourse.payments.ui.screen.addCard

import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toPresentation

data class AddCardUiState(
    val cardNumber: String = "",
    val expired: String = "",
    val cardOwner: String = "",
    val password: String = "",
    val bankUiModel: BankUiModel = BankType.NOT_SELECTED.toPresentation(),
    val errors: Set<AddCardError> = emptySet(),
    val submitted: Boolean = false,
) {
    val isFormValid: Boolean = errors.isEmpty()
    val cardNumberError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.CARD_NUMBER_INVALID } else null
    val expiredError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.EXPIRED_INVALID } else null
    val ownerError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.OWNER_INVALID } else null
    val passwordError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.PASSWORD_INVALID } else null
}
