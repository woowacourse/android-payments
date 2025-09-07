package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val cardNumber: String,
    val cardHolder: String,
    val expirationDate: ExpirationDateUiModel,
) : Parcelable
