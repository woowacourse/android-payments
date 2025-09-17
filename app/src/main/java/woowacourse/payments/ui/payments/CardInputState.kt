package woowacourse.payments.ui.payments

import woowacourse.payments.ui.payments.model.BankUiState

@JvmInline
value class CardInputState(
    val bankUiState: BankUiState,
)
