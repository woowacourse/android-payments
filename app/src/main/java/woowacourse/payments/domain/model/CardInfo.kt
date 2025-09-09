package woowacourse.payments.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfo(
    val cardNumber: String,
    val expirationDate: String,
    val userName: String,
    val password: String,
) : Parcelable
