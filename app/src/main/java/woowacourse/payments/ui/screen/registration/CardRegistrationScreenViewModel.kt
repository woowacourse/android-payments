package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.R
import woowacourse.payments.ui.common.StringResWithParams
import woowacourse.payments.ui.component.CardExpirationDateUiModel
import woowacourse.payments.ui.component.CardNumberUiModel
import woowacourse.payments.ui.component.CardPasswordUiModel
import woowacourse.payments.ui.component.CardholderNameUiModel

class CardRegistrationScreenViewModel(
    initialUiState: CardRegistrationScreenUiState = CardRegistrationScreenUiState(),
) {
    var uiState by mutableStateOf(initialUiState)
        private set

    var uiEvent by mutableStateOf<CardRegistrationScreenUiEvent?>(null)
        private set

    fun updateCardNumber(cardNumber: CardNumberUiModel) {
        uiState = uiState.copy(cardNumber = cardNumber)
    }

    fun updateCardExpirationDate(cardExpirationDate: CardExpirationDateUiModel) {
        uiState = uiState.copy(cardExpirationDate = cardExpirationDate)
    }

    fun updateCardholderName(cardholderName: CardholderNameUiModel) {
        uiState = uiState.copy(cardholderName = cardholderName)
    }

    fun updateCardPassword(cardPassword: CardPasswordUiModel) {
        uiState = uiState.copy(cardPassword = cardPassword)
    }

    fun registerCard() {
        if (!uiState.isSaveButtonEnabled) return
        val message =
            StringResWithParams(R.string.card_registration_screen_registration_card_success)
        uiEvent = CardRegistrationScreenUiEvent.ShowSnackbar(message)
    }
}
