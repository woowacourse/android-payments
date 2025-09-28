package woowacourse.payments.ui.screen.registration

import woowacourse.payments.ui.model.PaymentCardUiModel

sealed interface CardRegistrationScreenUiEvent {
    data class RegisteredCard(
        val paymentCard: PaymentCardUiModel,
    ) : CardRegistrationScreenUiEvent

    data object RegisterCardFailure : CardRegistrationScreenUiEvent

    data class UpdatedCard(
        val paymentCard: PaymentCardUiModel,
    ) : CardRegistrationScreenUiEvent
}
