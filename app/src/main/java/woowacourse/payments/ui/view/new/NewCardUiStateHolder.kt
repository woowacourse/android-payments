package woowacourse.payments.ui.view.new

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.state.CardCompanyState

class NewCardUiStateHolder private constructor(
    initialState: NewCardUiState,
) {
    private val _uiState = mutableStateOf(initialState)
    val uiState: NewCardUiState get() = _uiState.value

    fun modifyUiState(event: NewCardUiEvent) {
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
        val bottomSheetState = _uiState.value.isBottomSheetOpen
        _uiState.value = _uiState.value.copy(cardCompanyState = company, isBottomSheetOpen = !bottomSheetState)
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
                            cardCompanyState = restored.cardCompanyState,
                            isBottomSheetOpen = restored.isBottomSheetOpen,
                            mode = restored.mode,
                        ),
                    )
                },
            )

        fun NewCardUiStateHolder(mode: NewCardMode): NewCardUiStateHolder {
            val uiState =
                when (mode) {
                    NewCardMode.Add -> NewCardUiState()

                    is NewCardMode.Modify -> {
                        val card = mode.card
                        NewCardUiState(
                            number = card.number,
                            expireDate = card.expireDate,
                            ownerName = card.ownerName,
                            password = card.password,
                            cardCompanyState = CardCompanyState.Selected(card.company),
                            mode = mode,
                            isBottomSheetOpen = false,
                        )
                    }
                }

            return NewCardUiStateHolder(uiState)
        }
    }
}
