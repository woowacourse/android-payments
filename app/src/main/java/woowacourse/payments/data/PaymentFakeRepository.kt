package woowacourse.payments.data

import woowacourse.payments.ui.features.cartinput.CardUiState
import java.util.concurrent.atomic.AtomicInteger

object PaymentFakeRepository {
    private val idCounter = AtomicInteger(0)

    private val cardDatabase = mutableListOf<PaymentCardRecord>()

    fun getCardUiStateById(id: Int): CardUiState? = cardDatabase.find { it.id == id }?.cardUiState

    fun addCardToDB(cardUiState: CardUiState): Int {
        val newId = idCounter.incrementAndGet()
        val newStoredCard = PaymentCardRecord(id = newId, cardUiState = cardUiState)
        cardDatabase.add(newStoredCard)
        return newId
    }

    fun updateDBCard(
        id: Int,
        newCardUiState: CardUiState,
    ) {
        val index = cardDatabase.indexOfFirst { it.id == id }

        if (index != -1) {
            cardDatabase[index] =
                PaymentCardRecord(id = id, cardUiState = newCardUiState)
        }
    }
}
