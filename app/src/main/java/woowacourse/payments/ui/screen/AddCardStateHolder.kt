package woowacourse.payments.ui.screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

class AddCardStateHolder(
    initialShowSheet: Boolean,
) {
    var uiState by mutableStateOf(AddCardUiState(showCompanySheet = initialShowSheet))
        private set

    val cardPreview by derivedStateOf {
        CardUiModel.EMPTY.copy(cardCompany = uiState.selectedCompany.toUiModel())
    }

    fun onNumberChange(value: String) {
        uiState =
            uiState.copy(
                number = value,
                numberError = CardNumber.validationErrorType(value),
            )
    }

    fun onExpirationChange(value: String) {
        uiState =
            uiState.copy(
                expiration = value,
                expirationError = ExpirationDate.validationErrorType(value),
            )
    }

    fun onUserNameChange(value: String) {
        uiState =
            uiState.copy(
                userName = value,
                userNameError = UserName.validationErrorType(value),
            )
    }

    fun onPasswordChange(value: String) {
        uiState =
            uiState.copy(
                password = value,
                passwordError = Password.validationErrorType(value),
            )
    }

    fun onSelectCompany(cardCompany: CardCompanyType) {
        uiState =
            uiState.copy(
                selectedCompany = cardCompany,
                showCompanySheet = false,
            )
    }

    fun onDismissSheet() {
        uiState = uiState.copy(showCompanySheet = false)
    }

    fun onSaveClick(onAddCard: (Card) -> Unit) {
        if (uiState.selectedCompany == CardCompanyType.NOT_SELECTED) {
            uiState = uiState.copy(showCompanySheet = true)
            return
        }

        uiState =
            uiState.copy(
                numberError = CardNumber.validationErrorType(uiState.number),
                expirationError = ExpirationDate.validationErrorType(uiState.expiration),
                userNameError = UserName.validationErrorType(uiState.userName),
                passwordError = Password.validationErrorType(uiState.password),
            )
        if (!uiState.isSaveEnabled) return

        try {
            val card =
                Card(
                    cardNumber = CardNumber.create(uiState.number),
                    expirationDate = ExpirationDate.createFromRaw(uiState.expiration),
                    userName = UserName.create(uiState.userName),
                    password = Password.create(uiState.password),
                    type = uiState.selectedCompany,
                )
            onAddCard(card)
        } catch (_: IllegalArgumentException) {
            uiState =
                uiState.copy(
                    numberError = CardNumber.validationErrorType(uiState.number),
                    expirationError = ExpirationDate.validationErrorType(uiState.expiration),
                    userNameError = UserName.validationErrorType(uiState.userName),
                    passwordError = Password.validationErrorType(uiState.password),
                )
        }
    }
}
