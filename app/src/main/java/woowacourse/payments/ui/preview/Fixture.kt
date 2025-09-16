package woowacourse.payments.ui.preview

import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.PaymentCardUiModel

val paymentCardUiModelSample =
    PaymentCardUiModel(BankType.NON, "1234567812345678", "1225", "빰".repeat(30))

val paymentCardUiModelSamples =
    listOf(
        paymentCardUiModelSample,
        paymentCardUiModelSample,
        paymentCardUiModelSample,
    )
