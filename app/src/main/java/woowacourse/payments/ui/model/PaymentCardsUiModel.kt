package woowacourse.payments.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class PaymentCardsUiModel(
    val cards: List<PaymentCardUiModel>
)
