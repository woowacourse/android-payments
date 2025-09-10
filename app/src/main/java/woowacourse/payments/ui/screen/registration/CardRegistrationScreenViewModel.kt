package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardRegistrationScreenViewModel(
    initialUiState: CardRegistrationScreenUiState =
        CardRegistrationScreenUiState(
            cardNumber = CardNumberUiModel(),
            cardExpirationDate = CardExpirationDateUiModel(),
            cardholderName = CardholderNameUiModel(maxLength = CardholderName.MAX_LENGTH),
            cardPassword = CardPasswordUiModel(),
        ),
) {
    private var _uiState by mutableStateOf(initialUiState)
    val uiState: CardRegistrationScreenUiState get() = _uiState

    private var _uiEvent by mutableStateOf<CardRegistrationScreenUiEvent?>(null)
    val uiEvent: CardRegistrationScreenUiEvent? get() = _uiEvent?.also { _uiEvent = null }

    fun updateCardNumber(cardNumber: String) {
        runCatching { CardNumber.from(cardNumber) }
            .onSuccess { newValue ->
                _uiState = _uiState.copy(cardNumber = CardNumberUiModel.from(newValue))
            }.onFailure { exception ->
                if (exception !is CardNumber.CardNumberError) return@onFailure
                handleCardNumberError(cardNumber, exception)
            }
    }

    fun updateCardExpirationDate(cardExpirationDate: String) {
        runCatching { CardExpirationDate.from(cardExpirationDate) }
            .onSuccess { newValue ->
                val newExpirationDate = CardExpirationDateUiModel.from(newValue)
                _uiState = _uiState.copy(cardExpirationDate = newExpirationDate)
            }.onFailure { exception ->
                if (exception !is CardExpirationDate.CardExpirationDateError) return@onFailure
                handleCardExpirationDateError(cardExpirationDate, exception)
            }
    }

    fun updateCardholderName(cardholderName: String) {
        runCatching { CardholderName.from(cardholderName) }
            .onSuccess { newValue ->
                val newCardholderName = CardholderNameUiModel.from(newValue)
                _uiState = _uiState.copy(cardholderName = newCardholderName)
            }.onFailure { exception ->
                if (exception !is CardholderName.CardholderNameError) return@onFailure
                handleCardholderNameError(cardholderName, exception)
            }
    }

    fun updateCardPassword(cardPassword: String) {
        runCatching { CardPassword.from(cardPassword) }
            .onSuccess { newValue ->
                _uiState = _uiState.copy(cardPassword = CardPasswordUiModel.from(newValue))
            }.onFailure { exception ->
                if (exception !is CardPassword.CardPasswordError) return@onFailure
                handleCardPasswordError(cardPassword, exception)
            }
    }

    fun registerCard() {
        if (!_uiState.isSaveButtonEnabled) return
        _uiEvent = CardRegistrationScreenUiEvent.RegisteredCard(_uiState.toPaymentCard())
    }

    private fun handleCardNumberError(
        cardNumber: String,
        exception: CardNumber.CardNumberError,
    ) {
        when (exception) {
            is CardNumber.CardNumberError.InsufficientLength -> {
                val newCardNumber =
                    _uiState.cardNumber.copy(
                        cardNumber = cardNumber,
                        state = CardNumberUiModel.State.NOT_FILLED,
                    )
                _uiState = _uiState.copy(cardNumber = newCardNumber)
            }

            is CardNumber.CardNumberError.ExceedsLength,
            is CardNumber.CardNumberError.NonDigit,
            -> Unit
        }
    }

    private fun handleCardExpirationDateError(
        cardExpirationDate: String,
        exception: CardExpirationDate.CardExpirationDateError,
    ) {
        when (exception) {
            is CardExpirationDate.CardExpirationDateError.InsufficientLength -> {
                val newExpirationDate =
                    _uiState.cardExpirationDate.copy(
                        cardExpirationDate = cardExpirationDate,
                        state = CardExpirationDateUiModel.State.NOT_FILLED,
                    )
                _uiState = _uiState.copy(cardExpirationDate = newExpirationDate)
            }

            is CardExpirationDate.CardExpirationDateError.UnsupportedDate -> {
                val newExpirationDate =
                    _uiState.cardExpirationDate.copy(
                        cardExpirationDate = cardExpirationDate,
                        state = CardExpirationDateUiModel.State.INVALID_FORMAT,
                    )
                _uiState = _uiState.copy(cardExpirationDate = newExpirationDate)
            }

            is CardExpirationDate.CardExpirationDateError.ExceedsLength,
            is CardExpirationDate.CardExpirationDateError.NonDigit,
            -> Unit
        }
    }

    private fun handleCardholderNameError(
        cardholderName: String,
        exception: CardholderName.CardholderNameError,
    ) {
        when (exception) {
            is CardholderName.CardholderNameError.ExceedsLength,
            is CardholderName.CardholderNameError.InvalidFormat,
            -> Unit
        }
    }

    private fun handleCardPasswordError(
        cardPassword: String,
        exception: CardPassword.CardPasswordError,
    ) {
        when (exception) {
            is CardPassword.CardPasswordError.InsufficientLength -> {
                val newCardPassword =
                    _uiState.cardPassword.copy(
                        cardPassword = cardPassword,
                        state = CardPasswordUiModel.State.NOT_FILLED,
                    )

                _uiState = _uiState.copy(cardPassword = newCardPassword)
            }

            is CardPassword.CardPasswordError.ExceedsLength,
            is CardPassword.CardPasswordError.NonDigit,
            -> Unit
        }
    }

    private fun CardRegistrationScreenUiState.toPaymentCard() =
        PaymentCardUiModel(
            number = cardNumber,
            expirationDate = cardExpirationDate,
            cardholderName = cardholderName,
        )
}
