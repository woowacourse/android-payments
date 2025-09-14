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
    val errors: Set<AddCardError> = emptySet(),
) {
    val isFormValid: Boolean get() = errors.isEmpty()
    val cardNumberError: AddCardError? get() = errors.find { it == AddCardError.CARD_NUMBER_INVALID }
    val expiredError: AddCardError? get() = errors.find { it == AddCardError.EXPIRED_INVALID }
    val ownerError: AddCardError? get() = errors.find { it == AddCardError.OWNER_INVALID }
    val passwordError: AddCardError? get() = errors.find { it == AddCardError.PASSWORD_INVALID }

    fun validate(): AddCardUiState {
        val newErrors = mutableSetOf<AddCardError>()
        if (cardNumber?.isValid != true) newErrors.add(AddCardError.CARD_NUMBER_INVALID)
        if (expired?.isValid != true) newErrors.add(AddCardError.EXPIRED_INVALID)
        if (!cardOwner.isValid) newErrors.add(AddCardError.OWNER_INVALID)
        if (password?.isValid != true) newErrors.add(AddCardError.PASSWORD_INVALID)
        return copy(errors = newErrors)
    }

    fun toCardUiModel(): CardUiModel =
        CardUiModel(
            number = cardNumber?.value.orEmpty(),
            expired = expired?.value.orEmpty(),
            owner = cardOwner.value,
        )
}
