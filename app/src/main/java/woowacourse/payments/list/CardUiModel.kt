package woowacourse.payments.list

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class CardUiModel (
    val number: String,
    val expiry: String,
    val password: String,
    val name: String? = null,
): Parcelable