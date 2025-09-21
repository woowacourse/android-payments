package woowacourse.payments.ui.view.new

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.state.CardCompanyState

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
            is NewCardUiEvent.OnChangeCardCompany -> updateCardBankType(event.cardCompany)
        }
    }

    private fun updateNumber(number: String) {
        _uiState.value = _uiState.value.copy(number = number)
    }

    private fun updateExpireDate(expireDate: String) {
        _uiState.value = _uiState.value.copy(expireDate = expireDate)
    }

    private fun updateOwnerName(ownerName: String) {
        _uiState.value = _uiState.value.copy(ownerName = ownerName)
    }

    private fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    private fun updateCardBankType(company: CardCompanyState) {
        _uiState.value = _uiState.value.copy(company = company)
    }

    companion object {
        val Saver: Saver<NewCardUiStateHolder, NewCardUiState> =
            Saver(
                save = { holder: NewCardUiStateHolder -> holder.uiState },
                restore = { restored ->
                    NewCardUiStateHolder(
                        NewCardUiState(
                            number = restored.number,
                            expireDate = restored.expireDate,
                            ownerName = restored.ownerName,
                            password = restored.password,
                            company = restored.company,
                        ),
                    )
                },
            )
    }
}
