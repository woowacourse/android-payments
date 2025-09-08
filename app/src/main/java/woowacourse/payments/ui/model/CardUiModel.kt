package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
) : Parcelable