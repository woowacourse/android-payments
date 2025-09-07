package woowacourse.payments.ui.model

import woowacourse.payments.ui.model.ExpirationDateUiModel

data class PaymentCardUiModel(
    val cardNumber: String,
    val cardHolder: String,
    val expirationDate: ExpirationDateUiModel,
)
