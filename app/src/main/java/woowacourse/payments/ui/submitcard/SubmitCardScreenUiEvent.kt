package woowacourse.payments.ui.submitcard

sealed interface SubmitCardScreenUiEvent {
    data object ShowCardSubmitFailureMessage : SubmitCardScreenUiEvent

    data object ShowCardAddSuccessMessage : SubmitCardScreenUiEvent

    data object ShowCardEditSuccessMessage : SubmitCardScreenUiEvent

    data object ShowCardEditFailureMessage : SubmitCardScreenUiEvent
}
