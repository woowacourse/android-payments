package woowacourse.payments.ui.screen.addCard

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardUiModel

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

fun AddCardUiState.toCardUiModel(): CardUiModel =
    CardUiModel(
        number = cardNumber?.value.orEmpty(),
        expired = expired?.value.orEmpty(),
        owner = cardOwner.value,
    )
