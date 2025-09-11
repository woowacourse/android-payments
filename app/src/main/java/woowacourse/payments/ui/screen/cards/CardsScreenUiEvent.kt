package woowacourse.payments.ui.screen.cards

sealed interface CardsScreenUiEvent {
    data object None : CardsScreenUiEvent

    data object RegisteredCard : CardsScreenUiEvent
}
