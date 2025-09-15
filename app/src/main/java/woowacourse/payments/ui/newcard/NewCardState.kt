package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.company.model.CompanyUiModel
import java.time.format.DateTimeFormatter

class NewCardState {
    var cardCompany: CompanyUiModel? by mutableStateOf(null)
        private set
    var cardNumber: String by mutableStateOf("")
        private set
    var cardExpirationDate: String by mutableStateOf("")
        private set
    var cardHolderName: String by mutableStateOf("")
        private set
    var cardPassword: String by mutableStateOf("")
        private set

    val isCardCompanySelected: Boolean
        get() = cardCompany != null

    val isCardNumberValid: Boolean
        get() = runCatching { CardNumber.from(cardNumber) }.isSuccess

    val isCardExpirationDateValid: Boolean
        get() =
            runCatching {
                CardExpirationDate.from(cardExpirationDate, DATE_TIME_FORMATTER)
            }.fold(
                onSuccess = { !it.isExpired() },
                onFailure = { false },
            )

    val isCardHolderNameValid: Boolean
        get() = cardHolderName.isBlank() || runCatching { CardHolderName(cardHolderName.trim()) }.isSuccess

    val isCardPasswordValid: Boolean
        get() = runCatching { CardPassword(cardPassword) }.isSuccess

    val isCardValid: Boolean
        get() = isCardCompanySelected && isCardNumberValid && isCardExpirationDateValid && isCardHolderNameValid && isCardPasswordValid

    val card: CardUiModel?
        get() =
            cardCompany?.takeIf { isCardCompanySelected }?.let { company: CompanyUiModel ->
                CardUiModel(
                    companyName = company.name,
                    number = cardNumber,
                    expirationDate = cardExpirationDate,
                    holderName = cardHolderName.trim(),
                    color = company.color,
                )
            }

    fun onCompanySelected(company: CompanyUiModel) {
        cardCompany = company
    }

    fun onCardNumberChange(value: String) {
        cardNumber = value
    }

    fun onCardExpirationDateChange(value: String) {
        cardExpirationDate = value
    }

    fun onCardHolderNameChange(value: String) {
        cardHolderName = value
    }

    fun onCardPasswordChange(value: String) {
        cardPassword = value
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMyy")
    }
}
