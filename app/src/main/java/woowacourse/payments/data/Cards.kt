package woowacourse.payments.data

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.mapper.toCardUiModel


object CardStore {
    val cards = mutableListOf<Card>()

    fun createCard(newCard: Card) {
        cards.add(newCard)
    }

    fun updateCard(newCard: Card) {
        val index = cards.indexOfFirst { it.id == newCard.id }
        if (index == -1) return
        cards[index] = newCard
    }

    fun fetch(cardId: Long) =
        cards.find { it.id == cardId }?.toCardUiModel() ?: throw IllegalStateException()

    fun fetchAll() = cards.toList()
}