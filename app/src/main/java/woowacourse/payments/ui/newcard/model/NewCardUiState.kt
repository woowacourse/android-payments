package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.BankType

@Parcelize
data class NewCardUiState(
    val bankType: BankType? = null,
    val cardNumber: String = "",
    val expiryDate: String = "",
    @StringRes val expiryDateErrorTextRes: Int? = null,
    val ownerName: String = "",
    val password: String = "",
) : Parcelable
