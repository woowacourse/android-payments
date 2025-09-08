package woowacourse.payments.ui

data class CardsUiState(
    val cards: List<Card>,
) {
    val hasNoCard: Boolean get() = cards.isEmpty()
    val hasOneCard: Boolean get() = cards.size == 1
    val hasMultipleCard: Boolean get() = cards.size > 2
}

data class Card(
    val number: String,
    val owner: String,
    val expiredDate: String,
)
