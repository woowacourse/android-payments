package woowacourse.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val cardNumber: String,
    val expiry: String,
    val owner: String,
) : Parcelable

const val EXTRA_CARD = "extra_card"
