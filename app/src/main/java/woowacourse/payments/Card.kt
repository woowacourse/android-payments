package woowacourse.payments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val number: String,
    val owner: String,
    val expiredDate: String,
) : Parcelable
