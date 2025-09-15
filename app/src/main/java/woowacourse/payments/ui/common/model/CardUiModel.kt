package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val companyName: String,
    val color: Long,
    val number: String,
    val expirationDate: String,
    val holderName: String,
) : Parcelable
