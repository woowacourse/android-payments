package woowacourse.payments.data

import woowacourse.payments.ui.features.cardinput.CardUiState
import java.util.concurrent.atomic.AtomicInteger

object PaymentInMemoryRepository {
    private val idCounter = AtomicInteger(0)

    private val cardDatabase = mutableListOf<PaymentCardRecord>()

    fun findById(id: Int): CardUiState? = cardDatabase.find { it.id == id }?.cardUiState

    fun add(cardUiState: CardUiState): Int {
        val newId = idCounter.incrementAndGet()
        val newStoredCard = PaymentCardRecord(id = newId, cardUiState = cardUiState)
        cardDatabase.add(newStoredCard)
        return newId
    }

    fun update(
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
