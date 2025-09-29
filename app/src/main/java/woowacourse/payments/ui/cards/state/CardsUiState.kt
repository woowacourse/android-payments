package woowacourse.payments.ui.cards.state

import woowacourse.payments.ui.model.CardUiModel

sealed class CardsUiState {
    data object Empty : CardsUiState()

    data class Single(
        val card: CardUiModel,
    ) : CardsUiState()

    data class Multiple(
        val cards: List<CardUiModel>,
    ) : CardsUiState()
}
