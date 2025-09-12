package woowacourse.payments.cards

import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.Card

class CardsStateHolder {
    private val _cards = mutableStateListOf<Card>()
    val cards get() = _cards.toList()

    fun add(card: Card) {
        _cards += card
    }

    fun isCardsEmpty() = cards.isEmpty()

    fun shouldDisplayEmptyCard() = cards.size <= 1
}
