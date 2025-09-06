package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.R
import woowacourse.payments.domain.DefaultPaymentCardValidator
import woowacourse.payments.domain.PaymentCardValidator
import woowacourse.payments.ui.common.StringResWithParams

class CardRegistrationScreenViewModel(
    initialUiState: CardRegistrationScreenUiState = CardRegistrationScreenUiState(),
    private val paymentCardValidator: PaymentCardValidator = DefaultPaymentCardValidator(),
) {
    var uiState by mutableStateOf(initialUiState)
        private set

    var uiEvent by mutableStateOf<CardRegistrationScreenUiEvent?>(null)
        private set

    fun updateCardNumber(cardNumber: String) {
        uiState =
            uiState.copy(
                cardNumber = cardNumber,
                cardNumberValidationResult = paymentCardValidator.validateCardNumber(cardNumber),
            )
    }

    fun updateCardExpirationDate(cardExpirationDate: String) {
        uiState =
            uiState.copy(
                cardExpirationDate = cardExpirationDate,
                cardExpirationDateValidationResult =
                    paymentCardValidator.validateCardExpirationDate(cardExpirationDate),
            )
    }

    fun updateCardholderName(cardholderName: String) {
        uiState =
            uiState.copy(
                cardholderName = cardholderName,
                cardholderNameValidationResult =
                    paymentCardValidator.validateCardholderName(cardholderName),
            )
    }

    fun updateCardPassword(cardPassword: String) {
        uiState =
            uiState.copy(
                cardPassword = cardPassword,
                cardPasswordValidationResult =
                    paymentCardValidator.validateCardPassword(cardPassword),
            )
    }

    fun registerCard() {
        if (!uiState.isSaveButtonEnabled) return
        val message =
            StringResWithParams(R.string.card_registration_screen_registration_card_success)
        uiEvent = CardRegistrationScreenUiEvent.ShowSnackbar(message)
    }
}
