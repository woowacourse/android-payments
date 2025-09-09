package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val number: String,
    val expiredDate: String,
    val ownerName: String? = null,
    val password: String,
) : Parcelable
