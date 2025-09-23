package woowacourse.payments.domain

import woowacourse.payments.domain.model.PaymentCard

object PaymentCardStore {
    private val cards = mutableListOf<PaymentCard>()

    fun getAll(): List<PaymentCard> = cards.toList()

    fun add(card: PaymentCard) {
        cards.add(card)
    }

    fun update(card: PaymentCard) {
        val index = cards.indexOfFirst { it.id == card.id }
        if (index != -1) cards[index] = card
    }

    fun findById(cardId: String): PaymentCard? = cards.find { it.id == cardId }
}
