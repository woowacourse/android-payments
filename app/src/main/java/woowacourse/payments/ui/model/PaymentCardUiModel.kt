package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val cardNumber: CardNumberUiModel,
    val cardHolder: CardHolderUiModel,
    val expirationDate: ExpirationDateUiModel,
) : Parcelable
