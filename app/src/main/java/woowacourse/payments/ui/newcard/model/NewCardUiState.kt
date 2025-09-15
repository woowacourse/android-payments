package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewCardUiState(
    val cardNumber: String = "",
    val expiryDate: String = "",
    @StringRes val expiryDateErrorTextRes: Int? = null,
    val ownerName: String = "",
    val password: String = "",
) : Parcelable
