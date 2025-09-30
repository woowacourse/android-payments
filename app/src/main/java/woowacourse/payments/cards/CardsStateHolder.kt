package woowacourse.payments.cards

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.Card

class CardsStateHolder {
    private val _cards = mutableStateListOf<Card>()
    val cards get() = _cards.toList()

    var selectedCardIndex = mutableIntStateOf(0)

    fun add(card: Card) {
        _cards += card
    }

    fun replace(card: Card) {
        _cards.removeAt(selectedCardIndex.intValue)
        _cards.add(selectedCardIndex.intValue, card)
    }

    fun isCardsEmpty() = cards.isEmpty()

    fun shouldDisplayEmptyCard() = cards.size <= 1

    fun updateSelectedCardIndex(card: Card) {
        selectedCardIndex.intValue = cards.indexOf(card)
    }
}
