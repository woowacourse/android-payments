package woowacourse.payments.ui.newcard.update

sealed interface UpdateCardUiEvent {
    data object UpdateCard : UpdateCardUiEvent
}
