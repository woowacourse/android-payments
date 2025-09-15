package woowacourse.payments.ui.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
data class PaymentCardsUiState(
    val cards: List<PaymentCardUiModel> = emptyList(),
) : Parcelable
