package woowacourse.payments.ui.newcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.model.CompanyUiModel

@Parcelize
data class NewCardUiState(
    val cardCompany: CompanyUiModel? = null,
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = false,
    val cardExpirationDate: String = "",
    val isCardExpirationDateValid: Boolean = false,
    val cardHolderName: String = "",
    val isCardHolderNameValid: Boolean = false,
    val cardPassword: String = "",
    val isCardPasswordValid: Boolean = false,
) : Parcelable {
    val isCardValid: Boolean
        get() =
            cardCompany != null &&
                isCardNumberValid &&
                isCardExpirationDateValid &&
                isCardHolderNameValid &&
                isCardPasswordValid
}
