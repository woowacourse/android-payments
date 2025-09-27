package woowacourse.payments.ui.submitcard

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

sealed class SubmitCardStateHolder(
    private val initCard: CardUiModel,
) {
    var cardNumber: String by mutableStateOf(initCard.cardNumber)
        private set
    var expirationDate: String by mutableStateOf(initCard.expirationDate)
        private set
    var cardholderName: String by mutableStateOf(initCard.cardholderName)
        private set
    var passcode: String by mutableStateOf(initCard.passcode)
        private set
    private var cardCompany: CardCompanyUiModel by mutableStateOf(initCard.cardCompany)

    var isCardNumberError: Boolean by mutableStateOf(false)
        private set
    var isExpirationDateError: Boolean by mutableStateOf(false)
        private set
    var isPasscodeError: Boolean by mutableStateOf(false)
        private set

    var showCardCompanies: Boolean by mutableStateOf(true)
        private set

    var shouldMoveFocus: Boolean by mutableStateOf(false)
        private set

    var uiEvent: SubmitCardScreenUiEvent? by mutableStateOf(null)
        internal set

    val card: CardUiModel
        get() =
            CardUiModel(
                initCard.id,
                cardNumber,
                expirationDate,
                cardholderName,
                passcode,
                cardCompany,
            )

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
        showCardCompanies = false
    }

    fun onCardCompaniesRequested() {
        showCardCompanies = true
    }

    fun onEventDispatched() {
        uiEvent = null
    }

    fun onFocusMoved() {
        shouldMoveFocus = false
    }

    abstract fun checkSubmission(onSuccess: () -> Unit)

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

    class AddCardStateHolder : SubmitCardStateHolder(CardUiModel.EMPTY) {
        override fun checkSubmission(onSuccess: () -> Unit) {
            if (isError) {
                uiEvent = SubmitCardScreenUiEvent.ShowCardSubmitFailureMessage
                return
            }
            uiEvent = SubmitCardScreenUiEvent.ShowCardAddSuccessMessage
            onSuccess()
        }
    }

    class EditCardStateHolder(
        private val initCard: CardUiModel,
    ) : SubmitCardStateHolder(initCard) {
        val isChanged: Boolean get() = card != initCard

        override fun checkSubmission(onSuccess: () -> Unit) {
            if (isError) {
                uiEvent = SubmitCardScreenUiEvent.ShowCardSubmitFailureMessage
                return
            }
            if (!isChanged) {
                uiEvent = SubmitCardScreenUiEvent.ShowCardEditFailureMessage
                return
            }
            uiEvent = SubmitCardScreenUiEvent.ShowCardEditSuccessMessage
            onSuccess()
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
