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
import woowacourse.payments.domain.parser.ExpirationDateParser
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
                numberError = validateNumber(value),
            )
    }

    fun onExpirationChange(value: String) {
        uiState =
            uiState.copy(
                expiration = value,
                expirationError = validateExpiration(value),
            )
    }

    fun onUserNameChange(value: String) {
        uiState =
            uiState.copy(
                userName = value,
                userNameError = validateUserName(value),
            )
    }

    fun onPasswordChange(value: String) {
        uiState =
            uiState.copy(
                password = value,
                passwordError = validatePassword(value),
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

        val numberErr = validateNumber(uiState.number)
        val expirationErr = validateExpiration(uiState.expiration)
        val userNameErr = validateUserName(uiState.userName)
        val passwordErr = validatePassword(uiState.password)

        uiState =
            uiState.copy(
                numberError = numberErr,
                expirationError = expirationErr,
                userNameError = userNameErr,
                passwordError = passwordErr,
            )
        if (!uiState.isSaveEnabled) return

        val ym = ExpirationDateParser.parse(uiState.expiration)
        val card =
            Card(
                cardNumber = CardNumber.from(uiState.number),
                expirationDate = ExpirationDate.from(ym!!),
                userName = UserName.from(uiState.userName),
                password = Password.from(uiState.password),
                type = uiState.selectedCompany,
            )
        onAddCard(card)
    }

    private fun validateNumber(value: String) = CardNumber.validate(value)

    private fun validateExpiration(value: String) = ExpirationDate.validate(value)

    private fun validateUserName(value: String) = UserName.validate(value)

    private fun validatePassword(value: String) = Password.validate(value)
}
