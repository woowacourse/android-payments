package woowacourse.payments.ui.addcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName.Companion.CARDHOLDER_NAME_MAX_LENGTH
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.domain.Passcode.Companion.PASSCODE_REQUIRED_LENGTH
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.YearMonth

class AddCardScreenUiStateHolder {
    var cardNumber: String by mutableStateOf("")
        private set
    var expirationDate: String by mutableStateOf("")
        private set
    var cardholderName: String by mutableStateOf("")
        private set
    var passcode: String by mutableStateOf("")
        private set
    var cardCompany: CardCompanyUiModel by mutableStateOf(CardCompany.NONE.toUiModel())
        private set

    var isCardNumberError: Boolean by mutableStateOf(false)
        private set
    var isExpirationDateError: Boolean by mutableStateOf(false)
        private set
    var isPasscodeError: Boolean by mutableStateOf(false)
        private set

    var shouldMoveFocus: Boolean by mutableStateOf(false)
        private set

    val card: CardUiModel
        get() = CardUiModel(cardNumber, expirationDate, cardholderName, passcode, cardCompany)

    val isError: Boolean
        get() {
            updateCardNumberError()
            updateExpirationDateError()
            updatePasscodeError()
            return isCardNumberError || isExpirationDateError || isPasscodeError
        }

    fun onCardNumberChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(CardNumberFormat.REQUIRED_LENGTH)
        cardNumber = filteredValue
        updateCardNumberError()
        shouldMoveFocus = !isCardNumberError
    }

    fun onExpirationDateChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(ExpirationDateFormat.REQUIRED_LENGTH)
        expirationDate = filteredValue
        updateExpirationDateError()
        shouldMoveFocus = !isExpirationDateError
    }

    fun onCardholderNameChanged(newValue: String) {
        cardholderName = newValue.take(CARDHOLDER_NAME_MAX_LENGTH)
    }

    fun onPasscodeChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(PASSCODE_REQUIRED_LENGTH)
        passcode = filteredValue
        updatePasscodeError()
        shouldMoveFocus = !isPasscodeError
    }

    fun onCardCompanySelected(company: CardCompanyUiModel) {
        cardCompany = company
    }

    fun onFocusMoved() {
        shouldMoveFocus = false
    }

    private fun updateCardNumberError() {
        isCardNumberError = runCatching { CardNumber(cardNumber) }.isFailure
    }

    private fun updateExpirationDateError() {
        isExpirationDateError =
            runCatching {
                val yearMonth =
                    YearMonth.parse(expirationDate, ExpirationDateFormat.formatPattern)
                ExpirationDate(yearMonth)
            }.isFailure
    }

    private fun updatePasscodeError() {
        isPasscodeError = runCatching { Passcode(passcode) }.isFailure
    }

    companion object {
        val CARD_COMPANIES: List<CardCompanyUiModel> =
            CardCompany.entries
                .filter { cardCompany: CardCompany ->
                    cardCompany != CardCompany.NONE
                }.map(CardCompany::toUiModel)
    }
}
