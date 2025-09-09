package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCard(
    val cardNumber: CardNumber,
    val expireDate: ExpireDate,
    val ownerName: OwnerName,
    val password: String,
) : Parcelable {
    companion object {
        const val MAX_LENGTH_PASSWORD = 4
    }
}
