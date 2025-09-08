package woowacourse.payments.ui.payments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardRegistrationScreenUiState(
    val cardNumber: String = "",
    val cardExpirationDate: String = "",
    val cardExpirationDateErrorMessage: String? = null,
    val cardholderName: String = "",
    val cardPassword: String = "",
    val snackBarMessage: String? = null,
) : Parcelable
