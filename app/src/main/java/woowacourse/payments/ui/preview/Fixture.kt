package woowacourse.payments.ui.preview

import woowacourse.payments.ui.model.PaymentCardUiModel

val paymentCardUiModelSample =
    PaymentCardUiModel("1234567812345678", "1225", "빰".repeat(30), "1234")

val paymentCardUiModelSamples =
    listOf(
        paymentCardUiModelSample,
        paymentCardUiModelSample,
        paymentCardUiModelSample
    )