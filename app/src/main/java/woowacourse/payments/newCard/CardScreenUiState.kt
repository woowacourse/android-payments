package woowacourse.payments.newCard

import woowacourse.payments.list.CardUiModel

sealed class CardScreenUiState(
    val cards: List<CardUiModel>,
) {
    data object Empty : CardScreenUiState(emptyList())

    data class SingleCard(val card: CardUiModel) : CardScreenUiState(cards = listOf(card))

    data class MultipleCard(val cardList: List<CardUiModel>) : CardScreenUiState(cards = cardList)

    companion object {
        fun from(cards: List<CardUiModel>): CardScreenUiState {
            return when {
                cards.isEmpty() -> Empty
                cards.size == 1 -> SingleCard(cards.first())
                else -> MultipleCard(cards)
            }
        }
    }
}
