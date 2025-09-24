package woowacourse.payments.ui.cardupdate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.cardupdate.model.CardCompanyUiModel

@Parcelize
data class CardUpdateUiState(
    val cardCompany: CardCompanyUiModel? = null,
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = false,
    val cardExpirationDate: String = "",
    val isCardExpirationDateValid: Boolean = false,
    val cardHolderName: String = "",
    val isCardHolderNameValid: Boolean = true,
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
