package woowacourse.payments.ui.cards.single

import woowacourse.payments.ui.model.CardUiModel

sealed interface SingleCardsUiEvent {
    data class AddCard(val cards: List<CardUiModel>) : SingleCardsUiEvent
    data object UpdateCard : SingleCardsUiEvent
}