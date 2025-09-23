package woowacourse.payments.newcard

import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.cards.CardParcelable
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany

class NewCardStateHolder(
    card: CardParcelable?,
) {
    var newCardUiState = mutableStateOf(NewCardUiState())
        private set

    var cardCompanyUiState = mutableStateOf(CardCompanyUiState.from(CardCompany.NONE))
        private set

    var isEditMode = mutableStateOf(false)
        private set

    init {
        card?.let {
            val cardCompany = CardCompany.valueOf(card.cardCompany)
            cardCompanyUiState.value = CardCompanyUiState.from(cardCompany)
            newCardUiState.value = card.toUiStateOrNull() ?: NewCardUiState()
            isEditMode.value = true
        }
    }

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

    fun updateCardCompanyUiState(value: CardCompanyUiState) {
        cardCompanyUiState.value = value

        val isCardCompanySelected = value != CardCompanyUiState.from(CardCompany.NONE)
        updateIsCardCompanySelected(isCardCompanySelected)
    }

    fun getCard(): Result<Card> =
        Card.from(
            cardNumber = newCardUiState.value.cardNumber,
            expiredDate = newCardUiState.value.expiredDate,
            ownerName = newCardUiState.value.ownerName,
            password = newCardUiState.value.password,
            cardCompany = cardCompanyUiState.value.toDomain(),
        )

    private fun updateIsCardCompanySelected(value: Boolean) {
        newCardUiState.value = newCardUiState.value.copy(isCardCompanySelected = value)
    }
}
