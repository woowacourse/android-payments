package woowacourse.payments.ui.screen.registration

import woowacourse.payments.ui.model.PaymentCardUiModel

sealed interface CardRegistrationScreenUiEvent {
    data class RegisteredCard(
        val paymentCard: PaymentCardUiModel,
    ) : CardRegistrationScreenUiEvent
}
