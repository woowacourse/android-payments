package woowacourse.payments.domain

interface CardRepository {
    fun findAll(): List<Card>

    fun add(card: Card)

    fun update(
        cardId: Long,
        updateCard: Card,
    )

    fun findById(cardId: Long): Card
}
