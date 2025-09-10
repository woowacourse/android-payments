package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel

fun PaymentCardUiModel.toDomain(): PaymentCard =
    PaymentCard(cardNumber, cardExpirationDate, cardholderName)
