package woowacourse.payments.ui.cardcreate.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateCardUiState(
    val cardNumber: String = "",
    val expiryDate: String = "",
    @StringRes val expiryDateErrorTextRes: Int? = null,
    val ownerName: String = "",
    val password: String = "",
) : Parcelable
