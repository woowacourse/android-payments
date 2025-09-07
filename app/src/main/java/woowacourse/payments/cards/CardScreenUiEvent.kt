package woowacourse.payments.cards

sealed interface CardScreenUiEvent {
    object Idle : CardScreenUiEvent
    object CompleteAddCard : CardScreenUiEvent
}
