package woowacourse.payments.ui.screen

sealed interface AddCardUiEvent {
    data object ShowNoChangesToast : AddCardUiEvent

    data object ShowCardAddedToast : AddCardUiEvent
}
