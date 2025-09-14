package woowacourse.payments.ui.view.cards

import woowacourse.payments.domain.Card

sealed interface CardsUiState {
    data object EMPTY : CardsUiState

    data class SINGLE(
        val state: Card,
    ) : CardsUiState

    data class MULTIPLE(
        val state: List<Card>,
    ) : CardsUiState
}
