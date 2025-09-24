package woowacourse.payments.ui.debug.fixture

import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.toLocalBankUiModel

val paymentCardUiModelSample =
    PaymentCardUiModel(
        BankType.BC.toLocalBankUiModel(),
        "1234567812345678",
        "1225",
        "빰".repeat(30),
        "1234",
        1
    )

val paymentCardUiModelSamples =
    listOf(
        paymentCardUiModelSample,
        paymentCardUiModelSample,
        paymentCardUiModelSample,
    )
