package woowacourse.payments.ui.screen.cards

import woowacourse.payments.ui.model.CardUiModel

sealed interface CardsUiState {
    data object Empty : CardsUiState

    data class SingleCard(
        val card: CardUiModel,
    ) : CardsUiState

    data class MultipleCards(
        val cards: List<CardUiModel>,
    ) : CardsUiState
}
