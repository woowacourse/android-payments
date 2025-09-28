package woowacourse.payments.ui.cards.non

import woowacourse.payments.ui.model.CardUiModel

sealed interface NonCardsUiEvent {
    data class AddCard(val card: CardUiModel) : NonCardsUiEvent
}