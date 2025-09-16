package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.BankType

@Parcelize
data class NewCardUiState(
    val bankType: BankType = BankType.NON,
    val cardNumber: String = "",
    val expiryDate: String = "",
    @StringRes val expiryDateErrorTextRes: Int? = null,
    val ownerName: String = "",
    val password: String = "",
) : Parcelable
