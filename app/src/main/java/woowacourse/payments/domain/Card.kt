package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val number: String,
    val expiry: String,
    val password: String,
    val name: String? = null,
): Parcelable