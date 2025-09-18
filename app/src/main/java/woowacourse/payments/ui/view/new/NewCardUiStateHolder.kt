package woowacourse.payments.ui.view.new

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import woowacourse.payments.domain.CardCompany
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
            is NewCardUiEvent.OnChangeBankType -> updateCardBankType(event.cardCompany)
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

    private fun updateCardBankType(company: CardCompany) {
        _uiState.value = _uiState.value.copy(company = CardCompanyState.Selected(company))
    }

    companion object {
        private const val KEY_NUMBER = "number"
        private const val KEY_EXPIRE_DATE = "expireDate"
        private const val KEY_OWNER_NAME = "ownerName"
        private const val KEY_PASSWORD = "password"
        private const val KEY_COMPANY = "company"

        val Saver: Saver<NewCardUiStateHolder, Any> =
            mapSaver(
                save = { holder: NewCardUiStateHolder ->
                    with(holder.uiState) {
                        mapOf(
                            KEY_NUMBER to number,
                            KEY_EXPIRE_DATE to expireDate,
                            KEY_OWNER_NAME to ownerName,
                            KEY_PASSWORD to password,
                            KEY_COMPANY to company,
                        )
                    }
                },
                restore = { restored ->
                    val number = restored[KEY_NUMBER] as String
                    val expireDate = restored[KEY_EXPIRE_DATE] as String
                    val ownerName = restored[KEY_OWNER_NAME] as String
                    val password = restored[KEY_PASSWORD] as String
                    val company =
                        restored[KEY_COMPANY] as?
                            CardCompanyState.Selected ?: CardCompanyState.Empty

                    NewCardUiStateHolder(
                        NewCardUiState(
                            number = number,
                            expireDate = expireDate,
                            ownerName = ownerName,
                            password = password,
                            company = company,
                        ),
                    )
                },
            )
    }
}
