package woowacourse.payments.ui.model

import woowacourse.payments.domain.model.PaymentCard

fun PaymentCard.toUiModel(): PaymentCardUiModel =
    PaymentCardUiModel(
        cardNumber = cardNumber.value,
        expiry = expiry.value,
        owner = owner,
    )
