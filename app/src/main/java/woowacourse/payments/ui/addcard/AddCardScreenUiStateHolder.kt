package woowacourse.payments.ui.addcard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName.Companion.CARDHOLDER_NAME_MAX_LENGTH
import woowacourse.payments.domain.Passcode
import woowacourse.payments.domain.Passcode.Companion.PASSCODE_REQUIRED_LENGTH
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

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

    val isError: Boolean get() = isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value

    fun updateCardNumber(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(CardNumberFormat.REQUIRED_LENGTH)

        cardNumber.value = filteredValue
        isCardNumberError.value = runCatching { CardNumber(cardNumber.value) }.isFailure

        shouldMoveFocus.value =
            (!isCardNumberError.value && filteredValue.length == CardNumberFormat.REQUIRED_LENGTH)
    }

    fun updateExpirationDate(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(ExpirationDateFormat.REQUIRED_LENGTH)
        expirationDate.value = filteredValue

        isExpirationDateError.value = !ExpirationDateFormat.isValidFormat(filteredValue)

        shouldMoveFocus.value =
            (!isExpirationDateError.value && filteredValue.length == ExpirationDateFormat.REQUIRED_LENGTH)
    }

    fun updateCardholderName(newValue: String) {
        cardholderName.value = newValue.take(CARDHOLDER_NAME_MAX_LENGTH)
    }

    fun updatePasscode(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(PASSCODE_REQUIRED_LENGTH)

        passcode.value = filteredValue
        isPasscodeError.value = runCatching { Passcode(passcode.value) }.isFailure

        shouldMoveFocus.value =
            (!isPasscodeError.value && filteredValue.length == PASSCODE_REQUIRED_LENGTH)
    }

    fun validate() {
        isCardNumberError.value = cardNumber.value.length != CardNumberFormat.REQUIRED_LENGTH

        isExpirationDateError.value =
            expirationDate.value.length != ExpirationDateFormat.REQUIRED_LENGTH ||
            !ExpirationDateFormat.isValidFormat(expirationDate.value)

        isPasscodeError.value = passcode.value.length != PASSCODE_REQUIRED_LENGTH
    }

    fun onFocusMoved() {
        shouldMoveFocus.value = false
    }

    companion object {
        val CARD_COMPANIES: List<CardCompanyUiModel> =
            CardCompany.entries
                .filter { cardCompany: CardCompany ->
                    cardCompany != CardCompany.NONE
                }.map(CardCompany::toUiModel)
    }
}
