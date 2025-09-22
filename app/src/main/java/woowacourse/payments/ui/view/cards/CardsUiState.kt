package woowacourse.payments.ui.view.cards

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.state.CardState

sealed interface CardsUiState {
    data object EMPTY : CardsUiState

    data class SINGLE(
        val state: Card,
    ) : CardsUiState {
        val card: CardState.Registered
            get() = CardState.Registered(state)
    }

    data class MULTIPLE(
        val state: List<Card>,
    ) : CardsUiState {
        val cards: List<CardState.Registered>
            get() = state.map { CardState.Registered(it) }
    }
}
