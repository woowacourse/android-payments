package woowacourse.payments.ui.model

import woowacourse.payments.domain.PaymentCard

fun PaymentCard.toUiModel(): PaymentCardUiModel =
    PaymentCardUiModel(
        cardNumber = cardNumber,
        expiry = expiry,
        owner = owner,
    )
