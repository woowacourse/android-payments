package woowacourse.payments.ui.screen.cards

import woowacourse.payments.ui.model.CardUiModel

sealed interface CardsUiState {
    fun addCard(newCard: CardUiModel): CardsUiState =
        when (this) {
            is Empty -> SingleCard(newCard)
            is SingleCard -> MultipleCards(listOf(card, newCard))
            is MultipleCards -> MultipleCards(cards + newCard)
        }

    data object Empty : CardsUiState

    data class SingleCard(
        val card: CardUiModel,
    ) : CardsUiState

    data class MultipleCards(
        val cards: List<CardUiModel>,
    ) : CardsUiState
}
