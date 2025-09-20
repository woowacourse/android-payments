package woowacourse.payments.newCard

import woowacourse.payments.list.CardUiModel

sealed class CardScreenUiState {
    abstract val cards: List<CardUiModel>

    data object Empty : CardScreenUiState() {
        override val cards = emptyList<CardUiModel>()
    }

    data class SingleCard(val card: CardUiModel) : CardScreenUiState() {
        override val cards = listOf(card)
    }

    data class MultipleCard(val cardList: List<CardUiModel>) : CardScreenUiState() {
        override val cards = cardList
    }

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
