package woowacourse.payments.ui.screen.registration

import woowacourse.payments.ui.component.PaymentCardUiModel

sealed interface CardRegistrationScreenUiEvent {
    data class RegisteredCard(
        val paymentCard: PaymentCardUiModel,
    ) : CardRegistrationScreenUiEvent
}
