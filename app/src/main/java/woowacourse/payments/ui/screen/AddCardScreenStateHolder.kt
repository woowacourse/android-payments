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

class AddCardScreenStateHolder {
    var state by mutableStateOf(AddCardFormState())
        private set

    var showSheet by mutableStateOf(true)
        private set
    var selectedCardCompanyType by mutableStateOf(CardCompanyType.NOT_SELECTED)
        private set

    val cardForPreview by derivedStateOf {
        CardUiModel.EMPTY.copy(cardCompany = selectedCardCompanyType.toUiModel())
    }

    private fun validateAllFields() {
        val numberError =
            try {
                CardNumber.from(state.number)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidCardNumberLength
            }

        val expirationError =
            try {
                val yearMonth = ExpirationDateInputParser.parse(state.expiration)
                ExpirationDate.from(yearMonth)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.ExpiredDate
            } catch (_: DateTimeParseException) {
                ValidationErrorType.InvalidFormat
            }

        val userNameError =
            try {
                UserName.from(state.userName)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidUserNameLength
            }

        val passwordError =
            try {
                Password.from(state.password)
                null
            } catch (_: IllegalArgumentException) {
                ValidationErrorType.InvalidPasswordLength
            }

        state =
            state.copy(
                numberErrorType = numberError,
                expirationErrorType = expirationError,
                userNameErrorType = userNameError,
                passwordErrorType = passwordError,
                isSaveEnabled =
                    numberError == null &&
                        expirationError == null &&
                        userNameError == null &&
                        passwordError == null &&
                        selectedCardCompanyType != CardCompanyType.NOT_SELECTED,
            )
    }

    fun onSelectCardCompany(cardCompany: CardCompanyType) {
        selectedCardCompanyType = cardCompany
        showSheet = false
    }

    fun onDismissSheet() {
        showSheet = false
    }

    fun onSaveClick(onAddCard: (Card) -> Unit) {
        if (selectedCardCompanyType == CardCompanyType.NOT_SELECTED) {
            showSheet = true
            return
        }

        validateAllFields()
        if (!state.isSaveEnabled) return

        val yearMonth = ExpirationDateInputParser.parse(state.expiration)
        val card =
            Card(
                cardNumber = CardNumber.from(state.number),
                expirationDate = ExpirationDate.from(yearMonth),
                userName = UserName.from(state.userName),
                password = Password.from(state.password),
                type = selectedCardCompanyType,
            )
        onAddCard(card)
    }

    fun onNumberChange(value: String) {
        state = state.copy(number = value)
    }

    fun onExpirationChange(value: String) {
        state = state.copy(expiration = value)
    }

    fun onUserNameChange(value: String) {
        state = state.copy(userName = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }
}
