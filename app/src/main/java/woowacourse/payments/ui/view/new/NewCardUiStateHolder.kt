package woowacourse.payments.ui.view.new

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import woowacourse.payments.domain.Banks
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.state.BankState

class NewCardUiStateHolder(
    initialState: NewCardUiState = NewCardUiState(),
) {
    private val _uiState = mutableStateOf(initialState)
    val uiState: NewCardUiState get() = _uiState.value

    fun updateCard(event: NewCardUiEvent) {
        when (event) {
            is NewCardUiEvent.OnChangeCardNumber -> updateNumber(event.cardNumber)
            is NewCardUiEvent.OnChangeExpireDate -> updateExpireDate(event.expireDate)
            is NewCardUiEvent.OnChangeOwnerName -> updateOwnerName(event.ownerName)
            is NewCardUiEvent.OnChangePassword -> updatePassword(event.password)
            is NewCardUiEvent.OnChangeBankType -> updateCardBankType(event.banks)
        }
    }

    private fun updateNumber(number: String) {
        _uiState.value = _uiState.value.copy(card = _uiState.value.card.copy(number = number))
    }

    private fun updateExpireDate(expireDate: String) {
        _uiState.value =
            _uiState.value.copy(card = _uiState.value.card.copy(expireDate = expireDate))
    }

    private fun updateOwnerName(ownerName: String) {
        _uiState.value = _uiState.value.copy(card = _uiState.value.card.copy(ownerName = ownerName))
    }

    private fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(card = _uiState.value.card.copy(password = password))
    }

    private fun updateCardBankType(company: Banks) {
        _uiState.value =
            _uiState.value.copy(card = _uiState.value.card.copy(bank = BankState.Bank(company)))
    }

    companion object {
        private const val KEY_CARDS = "cards"

        val Saver: Saver<NewCardUiStateHolder, Any> =
            mapSaver(
                save = { holder: NewCardUiStateHolder ->
                    mapOf(
                        KEY_CARDS to holder.uiState.card.toSerializationCard(),
                    )
                },
                restore = { restored ->
                    val card = (restored[KEY_CARDS] as SerializationCard).toDomain()
                    NewCardUiStateHolder(
                        NewCardUiState(card = card),
                    )
                },
            )
    }
}
