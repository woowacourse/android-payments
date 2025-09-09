package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCard(
    val cardNumber: CardNumber,
    val expireDate: ExpireDate,
    val ownerName: OwnerName,
    val password: Password,
) : Parcelable
