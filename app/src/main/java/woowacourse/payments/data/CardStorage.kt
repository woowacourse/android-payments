package woowacourse.payments.data

import java.util.concurrent.atomic.AtomicLong
import woowacourse.payments.domain.Card

object CardStorage {
    private var insertId = AtomicLong(1)
    private val cards = mutableMapOf<Long, Card>()

    fun findAll(): List<Card> = cards.map { it.value }

    fun add(card: Card) {
        val id = insertId.getAndIncrement()
        cards[id] = card.copy(id = id)
    }

    fun update(
        cardId: Long,
        updateCard: Card,
    ) {
        cards[cardId] = updateCard.copy(id = cardId)
    }

    fun findById(cardId: Long): Card = cards[cardId] ?: throw IllegalArgumentException("등록되지 않은 값 입니다.")
}
