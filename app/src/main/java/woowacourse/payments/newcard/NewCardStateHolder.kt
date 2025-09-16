package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany

class NewCardStateHolder {
    var cardNumber: String by mutableStateOf("")
        private set

    var expiredDate: String by mutableStateOf("")
        private set

    var ownerName: String by mutableStateOf("")
        private set

    var password: String by mutableStateOf("")
        private set

    var selectedCardCompany: CardCompany by mutableStateOf(CardCompany.NONE)

    val isCardSelected: Boolean get() = selectedCardCompany != CardCompany.NONE

    fun updateCardNumber(value: String) {
        cardNumber = value
    }

    fun updateExpiredDate(value: String) {
        expiredDate = value
    }

    fun updateOwnerName(value: String) {
        ownerName = value
    }

    fun updatePassword(value: String) {
        password = value
    }

    fun getCard(): Result<Card> =
        Card.from(
            cardNumber,
            expiredDate,
            ownerName,
            password,
            selectedCardCompany,
        )
}
