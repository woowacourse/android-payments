package woowacourse.payments.ui

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password

data class AddCardUiState(
    val cardNumber: CardNumber? = null,
    val expired: Expired? = null,
    val cardOwner: CardOwner = CardOwner(""),
    val password: Password? = null,
    val showValidationError: Boolean = false,
) {
    val isFormValid: Boolean
        get() =
            (cardNumber?.isValid == true) &&
                (expired?.isValid == true) && cardOwner.isValid && (password?.isValid == true)
}
