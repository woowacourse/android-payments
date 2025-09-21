package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.model.toUiModel
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel
import java.time.format.DateTimeFormatter

class NewCardStateHolder(
    uiState: NewCardUiState = NewCardUiState(),
) {
    var uiState: NewCardUiState by mutableStateOf(uiState)
        private set

    val card: CardUiModel? get() = uiState.toUiModel()

    fun onCompanySelected(company: CardCompanyUiModel) {
        uiState = uiState.copy(cardCompany = company)
    }

    fun onCardNumberChange(value: String) {
        val isValid: Boolean = runCatching { CardNumber.from(value) }.isSuccess
        uiState = uiState.copy(cardNumber = value, isCardNumberValid = isValid)
    }

    fun onCardExpirationDateChange(value: String) {
        val isValid: Boolean =
            runCatching {
                CardExpirationDate.from(value, DATE_TIME_FORMATTER)
            }.fold(
                onSuccess = { !it.isExpired() },
                onFailure = { false },
            )
        uiState =
            uiState.copy(
                cardExpirationDate = value,
                isCardExpirationDateValid = isValid,
            )
    }

    fun onCardHolderNameChange(value: String) {
        val isValid: Boolean =
            value.isBlank() || runCatching { CardHolderName(value.trim()) }.isSuccess
        uiState =
            uiState.copy(cardHolderName = value, isCardHolderNameValid = isValid)
    }

    fun onCardPasswordChange(value: String) {
        val isValid: Boolean = runCatching { CardPassword(value) }.isSuccess
        uiState = uiState.copy(cardPassword = value, isCardPasswordValid = isValid)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMyy")
    }
}
