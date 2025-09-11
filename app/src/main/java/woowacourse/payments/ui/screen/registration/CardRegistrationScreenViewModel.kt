package woowacourse.payments.ui.screen.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.ui.extension.update
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
    private var _uiState = MutableLiveData(initialUiState)
    val uiState: LiveData<CardRegistrationScreenUiState> get() = _uiState

    private var _uiEvent =
        MutableLiveData<CardRegistrationScreenUiEvent>(CardRegistrationScreenUiEvent.None)
    val uiEvent: LiveData<CardRegistrationScreenUiEvent> =
        _uiEvent.also { _uiEvent.value = CardRegistrationScreenUiEvent.None }

    fun updateCardNumber(cardNumber: String) {
        runCatching { CardNumber.from(cardNumber) }
            .onSuccess { newValue ->
                _uiState.update { copy(cardNumber = CardNumberUiModel.from(newValue)) }
            }.onFailure { exception ->
                if (exception !is CardNumber.CardNumberError) return@onFailure
                handleCardNumberError(cardNumber, exception)
            }
    }

    fun updateCardExpirationDate(cardExpirationDate: String) {
        runCatching { CardExpirationDate.from(cardExpirationDate) }
            .onSuccess { newValue ->
                val newExpirationDate = CardExpirationDateUiModel.from(newValue)
                _uiState.update { copy(cardExpirationDate = newExpirationDate) }
            }.onFailure { exception ->
                if (exception !is CardExpirationDate.CardExpirationDateError) return@onFailure
                handleCardExpirationDateError(cardExpirationDate, exception)
            }
    }

    fun updateCardholderName(cardholderName: String) {
        runCatching { CardholderName.from(cardholderName) }
            .onSuccess { newValue ->
                val newCardholderName = CardholderNameUiModel.from(newValue)
                _uiState.update { copy(cardholderName = newCardholderName) }
            }.onFailure { exception ->
                if (exception !is CardholderName.CardholderNameError) return@onFailure
                handleCardholderNameError(exception)
            }
    }

    fun updateCardPassword(cardPassword: String) {
        runCatching { CardPassword.from(cardPassword) }
            .onSuccess { newValue ->
                _uiState.update { copy(cardPassword = CardPasswordUiModel.from(newValue)) }
            }.onFailure { exception ->
                if (exception !is CardPassword.CardPasswordError) return@onFailure
                handleCardPasswordError(cardPassword, exception)
            }
    }

    fun registerCard() {
        if (_uiState.value?.isSaveButtonEnabled == false) return
        val paymentCard = _uiState.value?.toPaymentCard() ?: return
        _uiEvent.update { CardRegistrationScreenUiEvent.RegisteredCard(paymentCard) }
    }

    private fun handleCardNumberError(
        newCardNumber: String,
        exception: CardNumber.CardNumberError,
    ) {
        when (exception) {
            is CardNumber.CardNumberError.InsufficientLength -> {
                _uiState.update {
                    cardNumber
                        .copy(number = newCardNumber, state = CardNumberUiModel.State.NOT_FILLED)
                        .let { copied -> copy(cardNumber = copied) }
                }
            }

            is CardNumber.CardNumberError.ExceedsLength,
            is CardNumber.CardNumberError.NonDigit,
            -> Unit
        }
    }

    private fun handleCardExpirationDateError(
        newCardExpirationDate: String,
        exception: CardExpirationDate.CardExpirationDateError,
    ) {
        when (exception) {
            is CardExpirationDate.CardExpirationDateError.InsufficientLength -> {
                _uiState.update {
                    cardExpirationDate
                        .copy(
                            expirationDate = newCardExpirationDate,
                            state = CardExpirationDateUiModel.State.NOT_FILLED,
                        ).let { copied -> copy(cardExpirationDate = copied) }
                }
            }

            is CardExpirationDate.CardExpirationDateError.UnsupportedDate -> {
                _uiState.update {
                    cardExpirationDate
                        .copy(
                            expirationDate = newCardExpirationDate,
                            state = CardExpirationDateUiModel.State.INVALID_FORMAT,
                        ).let { copied -> copy(cardExpirationDate = copied) }
                }
            }

            is CardExpirationDate.CardExpirationDateError.ExceedsLength,
            is CardExpirationDate.CardExpirationDateError.NonDigit,
            -> Unit
        }
    }

    private fun handleCardholderNameError(exception: CardholderName.CardholderNameError) {
        when (exception) {
            is CardholderName.CardholderNameError.ExceedsLength,
            is CardholderName.CardholderNameError.InvalidFormat,
            -> Unit
        }
    }

    private fun handleCardPasswordError(
        newCardPassword: String,
        exception: CardPassword.CardPasswordError,
    ) {
        when (exception) {
            is CardPassword.CardPasswordError.InsufficientLength -> {
                _uiState.update {
                    cardPassword
                        .copy(
                            password = newCardPassword,
                            state = CardPasswordUiModel.State.NOT_FILLED,
                        ).let { copied -> copy(cardPassword = copied) }
                }
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
