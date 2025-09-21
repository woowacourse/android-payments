package woowacourse.payments.ui.addcard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    val cardNumber: MutableState<String> = mutableStateOf("")
    val expirationDate: MutableState<String> = mutableStateOf("")
    val cardholderName: MutableState<String> = mutableStateOf("")
    val passcode: MutableState<String> = mutableStateOf("")
    val cardCompany: MutableState<CardCompanyUiModel> = mutableStateOf(CardCompany.NONE.toUiModel())

    val isCardNumberError: MutableState<Boolean> = mutableStateOf(false)
    val isExpirationDateError: MutableState<Boolean> = mutableStateOf(false)
    val isPasscodeError: MutableState<Boolean> = mutableStateOf(false)

    val shouldMoveFocus: MutableState<Boolean> = mutableStateOf(false)

    val card: CardUiModel
        get() =
            CardUiModel(
                cardNumber.value,
                expirationDate.value,
                cardholderName.value,
                passcode.value,
                cardCompany.value,
            )

    val isError: Boolean
        get() {
            updateCardNumberError()
            updateExpirationDateError()
            updatePasscodeError()
            return isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value
        }

    fun onCardNumberChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(CardNumberFormat.REQUIRED_LENGTH)
        cardNumber.value = filteredValue
        updateCardNumberError()
        shouldMoveFocus.value = !isCardNumberError.value
    }

    fun onExpirationDateChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(ExpirationDateFormat.REQUIRED_LENGTH)
        expirationDate.value = filteredValue
        updateExpirationDateError()
        shouldMoveFocus.value = !isExpirationDateError.value
    }

    fun onCardholderNameChanged(newValue: String) {
        cardholderName.value = newValue.take(CARDHOLDER_NAME_MAX_LENGTH)
    }

    fun onPasscodeChanged(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(PASSCODE_REQUIRED_LENGTH)
        passcode.value = filteredValue
        updatePasscodeError()
        shouldMoveFocus.value = !isPasscodeError.value
    }

    fun onFocusMoved() {
        shouldMoveFocus.value = false
    }

    private fun updateCardNumberError() {
        isCardNumberError.value = runCatching { CardNumber(cardNumber.value) }.isFailure
    }

    private fun updateExpirationDateError() {
        isExpirationDateError.value =
            runCatching {
                val yearMonth =
                    YearMonth.parse(expirationDate.value, ExpirationDateFormat.formatPattern)
                ExpirationDate(yearMonth)
            }.isFailure
    }

    private fun updatePasscodeError() {
        isPasscodeError.value = runCatching { Passcode(passcode.value) }.isFailure
    }

    companion object {
        val CARD_COMPANIES: List<CardCompanyUiModel> =
            CardCompany.entries
                .filter { cardCompany: CardCompany ->
                    cardCompany != CardCompany.NONE
                }.map(CardCompany::toUiModel)
    }
}
