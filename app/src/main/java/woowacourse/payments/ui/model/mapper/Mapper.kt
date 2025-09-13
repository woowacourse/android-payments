package woowacourse.payments.ui.model.mapper

import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel

fun PaymentCard.toUiModel(): PaymentCardUiModel =
    PaymentCardUiModel(
        cardNumber = cardNumber.value,
        expiry = expiry.value,
        owner = owner,
    )
