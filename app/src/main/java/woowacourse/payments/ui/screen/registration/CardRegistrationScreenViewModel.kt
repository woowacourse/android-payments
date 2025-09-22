package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.saveable.Saver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.ui.extension.update
import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

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

    private var _uiEvent = MutableLiveData<CardRegistrationScreenUiEvent?>()
    val uiEvent: LiveData<CardRegistrationScreenUiEvent?> = _uiEvent.also { _uiEvent.value = null }

    fun updateBank(bankType: BankTypeUiModel) {
        _uiState.update { copy(bankType = bankType, shouldOpenBankSelector = false) }
    }

    fun openBankSelectorBottomSheet() {
        _uiState.update { copy(shouldOpenBankSelector = true) }
    }

    fun closeBankSelectorBottomSheet() {
        _uiState.update { copy(shouldOpenBankSelector = false) }
    }

    fun updateCardNumber(cardNumber: String) {
        runCatching { CardNumber.from(cardNumber) }
            .onSuccess { newValue ->
                _uiState.update { copy(cardNumber = CardNumberUiModel.from(newValue)) }
            }.onFailure { exception ->
                if (exception !is CardNumber.CardNumberException) return@onFailure
                handleCardNumberError(cardNumber, exception)
            }
    }

    fun updateCardExpirationDate(cardExpirationDate: String) {
        runCatching { CardExpirationDate.from(cardExpirationDate) }
            .onSuccess { newValue ->
                val newExpirationDate = CardExpirationDateUiModel.from(newValue)
                _uiState.update { copy(cardExpirationDate = newExpirationDate) }
            }.onFailure { exception ->
                if (exception !is CardExpirationDate.CardExpirationDateException) return@onFailure
                handleCardExpirationDateError(cardExpirationDate, exception)
            }
    }

    fun updateCardholderName(cardholderName: String) {
        runCatching { CardholderName.from(cardholderName) }
            .onSuccess { newValue ->
                val newCardholderName = CardholderNameUiModel.from(newValue)
                _uiState.update { copy(cardholderName = newCardholderName) }
            }.onFailure { exception ->
                if (exception !is CardholderName.CardholderNameException) return@onFailure
                handleCardholderNameError(exception)
            }
    }

    fun updateCardPassword(cardPassword: String) {
        runCatching { CardPassword.from(cardPassword) }
            .onSuccess { newValue ->
                _uiState.update { copy(cardPassword = CardPasswordUiModel.from(newValue)) }
            }.onFailure { exception ->
                if (exception !is CardPassword.CardPasswordException) return@onFailure
                handleCardPasswordError(cardPassword, exception)
            }
    }

    fun registerCard() {
        val currentUiState = _uiState.value ?: return
        if (!currentUiState.canRegisterCard) return

        val paymentCard = currentUiState.toPaymentCardUiModel()
        _uiEvent.value = CardRegistrationScreenUiEvent.RegisteredCard(paymentCard)
    }

    private fun handleCardNumberError(
        newCardNumber: String,
        exception: CardNumber.CardNumberException,
    ) {
        when (exception) {
            is CardNumber.CardNumberException.InvalidLengthException -> {
                if (exception.kind == CardNumber.CardNumberException.InvalidLengthException.Kind.EXCEEDS) return
                _uiState.update {
                    cardNumber
                        .copy(number = newCardNumber, state = CardNumberUiModel.State.NOT_FILLED)
                        .let { copied -> copy(cardNumber = copied) }
                }
            }

            is CardNumber.CardNumberException.NonDigitException -> Unit
        }
    }

    private fun handleCardExpirationDateError(
        newCardExpirationDate: String,
        exception: CardExpirationDate.CardExpirationDateException,
    ) {
        when (exception) {
            is CardExpirationDate.CardExpirationDateException.InvalidLengthException -> {
                if (exception.kind == CardExpirationDate.CardExpirationDateException.InvalidLengthException.Kind.EXCEEDS) return
                _uiState.update {
                    cardExpirationDate
                        .copy(
                            expirationDate = newCardExpirationDate,
                            state = CardExpirationDateUiModel.State.NOT_FILLED,
                        ).let { copied -> copy(cardExpirationDate = copied) }
                }
            }

            is CardExpirationDate.CardExpirationDateException.UnsupportedDateException -> {
                _uiState.update {
                    cardExpirationDate
                        .copy(
                            expirationDate = newCardExpirationDate,
                            state = CardExpirationDateUiModel.State.INVALID_FORMAT,
                        ).let { copied -> copy(cardExpirationDate = copied) }
                }
            }

            is CardExpirationDate.CardExpirationDateException.NonDigitException -> Unit
        }
    }

    private fun handleCardholderNameError(exception: CardholderName.CardholderNameException) {
        when (exception) {
            is CardholderName.CardholderNameException.LengthExceededException,
            is CardholderName.CardholderNameException.InvalidFormatException,
            -> Unit
        }
    }

    private fun handleCardPasswordError(
        newCardPassword: String,
        exception: CardPassword.CardPasswordException,
    ) {
        when (exception) {
            is CardPassword.CardPasswordException.InvalidLengthException -> {
                if (exception.kind == CardPassword.CardPasswordException.InvalidLengthException.Kind.EXCEEDS) return
                _uiState.update {
                    cardPassword
                        .copy(
                            password = newCardPassword,
                            state = CardPasswordUiModel.State.NOT_FILLED,
                        ).let { copied -> copy(cardPassword = copied) }
                }
            }

            is CardPassword.CardPasswordException.NonDigitException -> Unit
        }
    }

    companion object {
        val saver: Saver<CardRegistrationScreenViewModel, CardRegistrationScreenUiState> =
            Saver(
                save = { viewModel -> viewModel.uiState.value },
                restore = { uiState -> CardRegistrationScreenViewModel(uiState) },
            )
    }
}
