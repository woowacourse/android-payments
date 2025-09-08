package woowacourse.payments.ui.screen.registration

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.component.CardExpirationDateUiModel
import woowacourse.payments.ui.component.CardNumberUiModel
import woowacourse.payments.ui.component.CardPasswordUiModel
import woowacourse.payments.ui.component.CardholderNameUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val cardNumber: CardNumberUiModel = CardNumberUiModel(""),
    val cardExpirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(""),
    val cardholderName: CardholderNameUiModel = CardholderNameUiModel(""),
    val cardPassword: CardPasswordUiModel = CardPasswordUiModel(""),
) : Parcelable {
    @IgnoredOnParcel
    val isSaveButtonEnabled: Boolean =
        cardNumber.isFilled && cardExpirationDate.isValid && cardPassword.isFilled
}
