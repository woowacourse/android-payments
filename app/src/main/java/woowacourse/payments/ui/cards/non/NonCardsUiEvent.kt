package woowacourse.payments.ui.cards.non


sealed interface NonCardsUiEvent {
    data class AddedCard(val cardId: Long) : NonCardsUiEvent
}