package woowacourse.payments.ui.screen.registration

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val cardNumber: CardNumberUiModel,
    val cardExpirationDate: CardExpirationDateUiModel,
    val cardholderName: CardholderNameUiModel,
    val cardPassword: CardPasswordUiModel,
) : Parcelable {
    @IgnoredOnParcel
    val isSaveButtonEnabled: Boolean =
        cardNumber.isValid && cardExpirationDate.isValid && cardholderName.isValid && cardPassword.isValid

    @IgnoredOnParcel
    val cardNumberErrorMessageResId: Int? =
        when (cardNumber.state) {
            CardNumberUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            else -> null
        }

    @IgnoredOnParcel
    val cardExpirationDateErrorMessageResId: Int? =
        when (cardExpirationDate.state) {
            CardExpirationDateUiModel.State.INVALID_FORMAT -> R.string.card_expiration_date_invalid_date_error_message
            CardExpirationDateUiModel.State.EXPIRED -> R.string.card_expiration_date_expired_error_message
            else -> null
        }

    @IgnoredOnParcel
    val cardholderNameErrorMessageResId: Int? =
        when (cardholderName.state) {
            CardholderNameUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            else -> null
        }

    @IgnoredOnParcel
    val cardPasswordErrorMessageResId: Int? =
        when (cardPassword.state) {
            CardPasswordUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            else -> null
        }

    fun toPaymentCardUiModel(): PaymentCardUiModel =
        PaymentCardUiModel(
            number = cardNumber,
            expirationDate = cardExpirationDate,
            cardholderName = cardholderName,
        )
}
