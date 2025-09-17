package woowacourse.payments.newCard

import woowacourse.payments.list.CardUiModel

sealed class CardScreenUiState {
    abstract fun cards(): List<CardUiModel>

    data object Empty : CardScreenUiState() {
        override fun cards() = emptyList<CardUiModel>()
    }

    data class SingleCard(val card: CardUiModel) : CardScreenUiState() {
        override fun cards() = listOf(card)
    }
    data class MultipleCard(val cards: List<CardUiModel>) : CardScreenUiState() {
        override fun cards() = cards
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

//fun CardScreenUiState.cards(): List<CardUiModel> =
//    when (this) {
//        is CardScreenUiState.Empty -> emptyList()
//        is CardScreenUiState.SingleCard -> listOf(this.card)
//        is CardScreenUiState.MultipleCard -> this.cards
//    }
