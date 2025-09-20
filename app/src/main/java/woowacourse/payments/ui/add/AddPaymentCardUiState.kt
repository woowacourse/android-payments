package woowacourse.payments.ui.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.BankType

@Parcelize
data class AddPaymentCardUiState(
    val cardNumber: String = "",
    val expiry: String = "",
    val owner: String = "",
    val pin: String = "",
    val bank: BankType = BankType.NOT_SELECTED,
    val isSheetVisible: Boolean = true,
) : Parcelable
