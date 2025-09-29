package woowacourse.payments.data

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardRepository

class CardRepositoryImpl : CardRepository {
    override fun findAll(): List<Card> = CardStorage.findAll()

    override fun add(card: Card) {
        CardStorage.add(card)
    }

    override fun update(
        cardId: Long,
        updateCard: Card,
    ) {
        CardStorage.update(cardId, updateCard)
    }

    override fun findById(cardId: Long): Card = CardStorage.findById(cardId)
}
