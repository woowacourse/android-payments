package woowacourse.payments.ui.cards.model

sealed interface CardsUiEvent {
    data object AddCard : CardsUiEvent
    data object UpdateCard : CardsUiEvent
}