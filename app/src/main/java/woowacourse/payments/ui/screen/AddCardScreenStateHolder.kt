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
import woowacourse.payments.domain.validator.ValidationErrorType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.text.ExpirationDateInputParser
import java.time.format.DateTimeParseException

class AddCardScreenStateHolder(
    initialShowSheet: Boolean,
) {
    var uiState by mutableStateOf(AddCardScreenUiState(showSheet = initialShowSheet))
        private set

    val cardForPreview by derivedStateOf {
        CardUiModel.EMPTY.copy(
            cardCompany = uiState.selectedCardCompanyType.toUiModel(),
        )
    }

    private fun validateAllFields(): AddCardFormState {
        val numberError =
            try {
                CardNumber.from(uiState.formState.number)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidCardNumberLength
            }

        val expirationError =
            try {
                val yearMonth = ExpirationDateInputParser.parse(uiState.formState.expiration)
                ExpirationDate.from(yearMonth)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.ExpiredDate
            } catch (_: DateTimeParseException) {
                ValidationErrorType.InvalidFormat
            }

        val userNameError =
            try {
                UserName.from(uiState.formState.userName)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidUserNameLength
            }

        val passwordError =
            try {
                Password.from(uiState.formState.password)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidPasswordLength
            }

        return uiState.formState.copy(
            numberErrorType = numberError,
            expirationErrorType = expirationError,
            userNameErrorType = userNameError,
            passwordErrorType = passwordError,
            isSaveEnabled =
                numberError == null &&
                    expirationError == null &&
                    userNameError == null &&
                    passwordError == null &&
                    uiState.selectedCardCompanyType != CardCompanyType.NOT_SELECTED,
        )
    }

    fun onSelectCardCompany(cardCompany: CardCompanyType) {
        uiState =
            uiState.copy(
                selectedCardCompanyType = cardCompany,
                showSheet = false,
            )
    }

    fun onDismissSheet() {
        uiState = uiState.copy(showSheet = false)
    }

    fun onSaveClick(onAddCard: (Card) -> Unit) {
        if (uiState.selectedCardCompanyType == CardCompanyType.NOT_SELECTED) {
            uiState = uiState.copy(showSheet = true)
            return
        }

        val updatedFormState = validateAllFields()
        uiState = uiState.copy(formState = updatedFormState)

        if (!updatedFormState.isSaveEnabled) return

        val yearMonth = ExpirationDateInputParser.parse(updatedFormState.expiration)
        val card =
            Card(
                cardNumber = CardNumber.from(updatedFormState.number),
                expirationDate = ExpirationDate.from(yearMonth),
                userName = UserName.from(updatedFormState.userName),
                password = Password.from(updatedFormState.password),
                type = uiState.selectedCardCompanyType,
            )
        onAddCard(card)
    }

    fun onNumberChange(value: String) {
        uiState = uiState.copy(formState = uiState.formState.copy(number = value))
    }

    fun onExpirationChange(value: String) {
        uiState = uiState.copy(formState = uiState.formState.copy(expiration = value))
    }

    fun onUserNameChange(value: String) {
        uiState = uiState.copy(formState = uiState.formState.copy(userName = value))
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(formState = uiState.formState.copy(password = value))
    }
}
