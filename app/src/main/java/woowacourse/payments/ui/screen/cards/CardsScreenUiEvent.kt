package woowacourse.payments.ui.screen.cards

sealed interface CardsScreenUiEvent {
    data object RegisteredCard : CardsScreenUiEvent

    data object UpdatedCard : CardsScreenUiEvent
}
