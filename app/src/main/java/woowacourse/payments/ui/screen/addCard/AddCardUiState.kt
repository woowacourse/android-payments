package woowacourse.payments.ui.screen.addCard

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardUiModel

data class AddCardUiState(
    val cardNumber: CardNumber = CardNumber(""),
    val expired: Expired = Expired(""),
    val cardOwner: CardOwner = CardOwner(""),
    val password: Password = Password(""),
    val errors: Set<AddCardError> = emptySet(),
) {
    val isFormValid: Boolean = errors.isEmpty()
    val cardNumberError: AddCardError? = errors.find { it == AddCardError.CARD_NUMBER_INVALID }
    val expiredError: AddCardError? = errors.find { it == AddCardError.EXPIRED_INVALID }
    val ownerError: AddCardError? = errors.find { it == AddCardError.OWNER_INVALID }
    val passwordError: AddCardError? = errors.find { it == AddCardError.PASSWORD_INVALID }

    fun validate(): AddCardUiState {
        val newErrors = mutableSetOf<AddCardError>()
        if (!cardNumber.isValid) newErrors.add(AddCardError.CARD_NUMBER_INVALID)
        if (!expired.isValid) newErrors.add(AddCardError.EXPIRED_INVALID)
        if (!cardOwner.isValid) newErrors.add(AddCardError.OWNER_INVALID)
        if (!password.isValid) newErrors.add(AddCardError.PASSWORD_INVALID)
        return copy(errors = newErrors)
    }

    fun toCardUiModel(): CardUiModel =
        CardUiModel(
            number = cardNumber.value,
            expired = expired.value,
            owner = cardOwner.value,
        )
}
