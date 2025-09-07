package woowacourse.payments.ui.view.cards

sealed interface CardScreenUiEvent {
    object Idle : CardScreenUiEvent
    object CompleteAddCard : CardScreenUiEvent
}
