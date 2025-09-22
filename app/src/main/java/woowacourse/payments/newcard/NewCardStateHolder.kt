package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany

class NewCardStateHolder {
    var newCardUiState = mutableStateOf(NewCardUiState())
        private set

    var selectedCardCompany: CardCompany by mutableStateOf(CardCompany.NONE)

    val isCardSelected: Boolean get() = selectedCardCompany != CardCompany.NONE

    fun updateCardNumber(value: String) {
        newCardUiState.value = newCardUiState.value.copy(cardNumber = value)
    }

    fun updateExpiredDate(value: String) {
        newCardUiState.value = newCardUiState.value.copy(expiredDate = value)
    }

    fun updateOwnerName(value: String) {
        newCardUiState.value = newCardUiState.value.copy(ownerName = value)
    }

    fun updatePassword(value: String) {
        newCardUiState.value = newCardUiState.value.copy(password = value)
    }

    fun getCard(): Result<Card> =
        Card.from(
            newCardUiState.value.cardNumber,
            newCardUiState.value.expiredDate,
            newCardUiState.value.ownerName,
            newCardUiState.value.password,
            selectedCardCompany,
        )
}
