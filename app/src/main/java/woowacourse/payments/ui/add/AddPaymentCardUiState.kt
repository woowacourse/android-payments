package woowacourse.payments.ui.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddPaymentCardUiState(
    val cardNumber: String = "",
    val expiry: String = "",
    val owner: String = "",
    val pin: String = "",
) : Parcelable
