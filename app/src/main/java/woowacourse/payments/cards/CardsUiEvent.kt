package woowacourse.payments.cards

sealed interface CardsUiEvent {
    data object AddCardSuccess : CardsUiEvent
}
