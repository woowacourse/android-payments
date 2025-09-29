package woowacourse.payments.ui.cards.fixture

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardRepository

data class FakeCardsRepositoryFixture(
    private val cards: List<Card>,
) : CardRepository {
    override fun findAll(): List<Card> = cards

    override fun add(card: Card) {}

    override fun update(
        cardId: Long,
        updateCard: Card,
    ) {
    }

    override fun findById(cardId: Long): Card = cards.first { it.id == cardId }
}
