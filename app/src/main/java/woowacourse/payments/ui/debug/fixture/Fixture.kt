package woowacourse.payments.ui.debug.fixture

import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toLocalBankUiModel

val cardUiModelSample =
    CardUiModel(
        BankType.BC.toLocalBankUiModel(),
        "1234567812345678",
        "1225",
        "빰".repeat(30),
        1,
    )

val cardUiModelSamples =
    listOf(
        cardUiModelSample,
        cardUiModelSample,
        cardUiModelSample,
    )
