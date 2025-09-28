package woowacourse.payments.view.cards

import woowacourse.payments.view.ui.model.CardUiModel

sealed interface CardsUiEvent {
    data object NavigateToCardAddition : CardsUiEvent

    data class NavigateToCardEditing(
        val card: CardUiModel,
    ) : CardsUiEvent
}
