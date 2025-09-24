package woowacourse.payments.ui.addcard

sealed interface SubmitCardScreenUiEvent {
    object ShowCardSubmitFailureMessage : SubmitCardScreenUiEvent

    object ShowCardAddSuccessMessage : SubmitCardScreenUiEvent

    object ShowCardEditSuccessMessage : SubmitCardScreenUiEvent

    object ShowCardEditFailureMessage : SubmitCardScreenUiEvent
}
