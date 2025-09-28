package woowacourse.payments.ui.cards.multi

sealed interface MultiCardsUiEvent {
    data object AddCard : MultiCardsUiEvent
    data object UpdateCard : MultiCardsUiEvent
}