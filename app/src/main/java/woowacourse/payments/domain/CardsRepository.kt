package woowacourse.payments.domain

interface CardsRepository {
    val cards: List<Card>

    fun addCard(card: Card)

    fun editCard(
        old: Card,
        new: Card,
    )
}

object MockCardsRepository : CardsRepository {
    private val _cards: MutableList<Card> = mutableListOf()
    override val cards: List<Card> get() = _cards.toList()

    override fun addCard(card: Card) {
        _cards.add(card)
    }

    override fun editCard(
        old: Card,
        new: Card,
    ) {
        val index = cards.indexOf(old)

        if (index == -1) throw IllegalArgumentException("편집할 카드가 존재하지 않습니다: $old")

        _cards[index] = new
    }
}
