package woowacourse.payments.ui.card.list

import woowacourse.payments.ui.model.CardUiModel

sealed class CardListUiState {
    data object Empty : CardListUiState()

    data class Single(
        val card: CardUiModel,
    ) : CardListUiState()

    data class Multiple(
        val cards: List<CardUiModel>,
    ) : CardListUiState()
}
