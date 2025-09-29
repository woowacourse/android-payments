package woowacourse.payments.ui.newcard.state

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.BankUiModel

@Parcelize
data class NewCardContentUiState(
    val bankUiModel: BankUiModel? = null,
    val cardNumber: String = "",
    val expiryDate: String = "",
    @StringRes val expiryDateErrorTextRes: Int? = null,
    val ownerName: String = "",
    val password: String = "",
) : Parcelable
