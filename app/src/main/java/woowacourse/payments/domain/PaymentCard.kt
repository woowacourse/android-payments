package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
data class PaymentCard(
    val cardNumber: CardNumber,
    val expireDate: YearMonth,
    val ownerName: String?,
    val password: String,
) : Parcelable {
    companion object {
        const val MAX_LENGTH_EXPIRE_DATE = 4
        const val MAX_LENGTH_OWNER_NAME = 30
        const val MAX_LENGTH_PASSWORD = 4
    }
}
