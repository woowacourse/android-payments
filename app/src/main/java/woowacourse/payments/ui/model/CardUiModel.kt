package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val numberDigits: String,
    val expiry: String,
    val holder: String,
) : Parcelable
