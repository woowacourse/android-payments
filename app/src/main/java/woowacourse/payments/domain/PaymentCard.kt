package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentCard(
    val cardNumber: String,
    val expirationDate: String,
    val cardOwnerName: String,
    val password: String,
) : Parcelable
