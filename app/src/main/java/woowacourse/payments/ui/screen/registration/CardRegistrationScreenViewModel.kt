package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardRegistrationScreenViewModel(
    initialUiState: CardRegistrationScreenUiState = CardRegistrationScreenUiState(),
) {
    private var _uiState by mutableStateOf(initialUiState)
    val uiState: CardRegistrationScreenUiState get() = _uiState

    private var _uiEvent by mutableStateOf<CardRegistrationScreenUiEvent?>(null)
    val uiEvent: CardRegistrationScreenUiEvent? get() = _uiEvent?.also { _uiEvent = null }

    fun updateCardNumber(cardNumber: CardNumberUiModel) {
        _uiState = _uiState.copy(cardNumber = cardNumber)
    }

    fun updateCardExpirationDate(cardExpirationDate: CardExpirationDateUiModel) {
        _uiState = _uiState.copy(cardExpirationDate = cardExpirationDate)
    }

    fun updateCardholderName(cardholderName: CardholderNameUiModel) {
        _uiState = _uiState.copy(cardholderName = cardholderName)
    }

    fun updateCardPassword(cardPassword: CardPasswordUiModel) {
        _uiState = _uiState.copy(cardPassword = cardPassword)
    }

    fun registerCard() {
        if (!_uiState.isSaveButtonEnabled) return
        _uiEvent = CardRegistrationScreenUiEvent.RegisteredCard(_uiState.toPaymentCard())
    }

    private fun CardRegistrationScreenUiState.toPaymentCard() =
        PaymentCardUiModel(
            number = cardNumber,
            expirationDate = cardExpirationDate,
            cardholderName = cardholderName,
        )
}
