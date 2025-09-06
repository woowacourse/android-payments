package woowacourse.payments.ui.payments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val cardNumber: CardNumberUiModel = CardNumberUiModel(),
    val cardExpirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val cardExpirationDateErrorMessage: String? = null,
    val cardholderName: String = "",
    val cardPassword: String = "",
    val isRegistrableCard: Boolean = false,
    val snackbarMessage: String? = null,
) : Parcelable
