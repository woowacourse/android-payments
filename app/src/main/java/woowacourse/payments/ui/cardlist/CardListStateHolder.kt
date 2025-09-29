package woowacourse.payments.ui.cardlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import woowacourse.payments.domain.Card

class CardListStateHolder {
    private val _cards: SnapshotStateList<Card> = mutableStateListOf()
    val cards: List<Card> get() = _cards.toList()

    fun updateCards(newCard: Card) {
        val index = cards.indexOfFirst { card: Card -> card.id == newCard.id }
        if (index == -1) {
            _cards.add(newCard)
            return
        }
        _cards[index] = newCard
    }
}
