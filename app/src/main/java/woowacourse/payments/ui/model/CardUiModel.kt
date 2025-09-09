package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val cardNumber: String,
    val expirationDate: String,
    val userName: String?,
    val password: String,
) : Parcelable
