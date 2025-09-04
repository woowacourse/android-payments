package woowacourse.payments.ui.cardcreate.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateCardState(
    val cardNumber: String = "",
    val expiryDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) : Parcelable

@Parcelize
data class CreateCardErrorState(
    val expiryDateMessage: String? = null,
) : Parcelable
