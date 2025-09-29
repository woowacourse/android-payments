package woowacourse.payments.data

import woowacourse.payments.ui.features.cardinput.CardUiState

data class PaymentCardRecord(
    val id: Int = -1,
    val cardUiState: CardUiState,
)
