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

sealed class SubmitCardScreenUiStateHolder(
    initCard: CardUiModel,
) {
    var cardNumber: String by mutableStateOf(initCard.cardNumber)
        private set
    var expirationDate: String by mutableStateOf(initCard.expirationDate)
        private set
    var cardholderName: String by mutableStateOf(initCard.cardholderName)
        private set
    var passcode: String by mutableStateOf(initCard.passcode)
        private set
    var cardCompany: CardCompanyUiModel by mutableStateOf(initCard.cardCompany)
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

    abstract val isError: Boolean

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

    internal fun updateCardNumberError() {
        isCardNumberError = runCatching { CardNumber(cardNumber) }.isFailure
    }

    internal fun updateExpirationDateError() {
        isExpirationDateError =
            runCatching {
                val yearMonth =
                    YearMonth.parse(expirationDate, ExpirationDateFormat.formatPattern)
                ExpirationDate(yearMonth)
            }.isFailure
    }

    internal fun updatePasscodeError() {
        isPasscodeError = runCatching { Passcode(passcode) }.isFailure
    }

    class AddCardScreenUiStateHolder : SubmitCardScreenUiStateHolder(CardUiModel.EMPTY) {
        override val isError: Boolean
            get() {
                updateCardNumberError()
                updateExpirationDateError()
                updatePasscodeError()
                return isCardNumberError || isExpirationDateError || isPasscodeError
            }
    }

    class EditCardScreenUiStateHolder(
        val initCard: CardUiModel,
    ) : SubmitCardScreenUiStateHolder(initCard) {
        override val isError: Boolean
            get() {
                if (card == initCard) return true
                updateCardNumberError()
                updateExpirationDateError()
                updatePasscodeError()
                return isCardNumberError || isExpirationDateError || isPasscodeError
            }
    }

    companion object {
        val CARD_COMPANIES: List<CardCompanyUiModel> =
            CardCompany.entries
                .filter { cardCompany: CardCompany ->
                    cardCompany != CardCompany.NONE
                }.map(CardCompany::toUiModel)
    }
}
