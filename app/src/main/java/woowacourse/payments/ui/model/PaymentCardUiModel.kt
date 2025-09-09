package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val cardNumber: String,
    val expiry: String,
    val owner: String,
) : Parcelable

const val EXTRA_CARD = "extra_card"
