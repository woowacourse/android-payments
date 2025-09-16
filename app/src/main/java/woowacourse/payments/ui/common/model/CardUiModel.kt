package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val numberDigits: String,
    val expiry: String,
    val holder: String,
) : Parcelable
