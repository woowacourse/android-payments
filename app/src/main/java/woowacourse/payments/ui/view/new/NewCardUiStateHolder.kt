package woowacourse.payments.ui.view.new

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.serialization.toSerializationCard

class NewCardUiStateHolder(
    initialUiState: NewCardUiState = NewCardUiState()
) {
    private val _uiState = mutableStateOf(initialUiState)
    val uiState: NewCardUiState get() = _uiState.value

    fun updateCard(event: NewCardUiEvent) {
        when (event) {
            is NewCardUiEvent.OnChangeCardNumber -> updateNumber(event.cardNumber)
            is NewCardUiEvent.OnChangeExpireDate -> updateExpireDate(event.expireDate)
            is NewCardUiEvent.OnChangeOwnerName -> updateOwnerName(event.ownerName)
            is NewCardUiEvent.OnChangePassword -> updatePassword(event.password)
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

    companion object {
        val Saver: Saver<NewCardUiStateHolder, SerializationCard> = Saver(
            save = { holder -> holder.uiState.card.toSerializationCard() },
            restore = { NewCardUiStateHolder(NewCardUiState(it.toDomain())) }
        )
    }
}
