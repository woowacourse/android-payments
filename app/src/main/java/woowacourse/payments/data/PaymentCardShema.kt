package woowacourse.payments.data

import woowacourse.payments.ui.features.cartinput.CardUiState

data class PaymentCardShema(
    val id: Int = -1,
    val cardUiState: CardUiState,
)
