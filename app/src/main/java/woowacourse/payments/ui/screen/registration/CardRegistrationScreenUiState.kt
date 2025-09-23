package woowacourse.payments.ui.screen.registration

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val cardId: Long? = null,
    val cardNumber: CardNumberUiModel = CardNumberUiModel(),
    val cardExpirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val cardholderName: CardholderNameUiModel = CardholderNameUiModel(),
    val cardPassword: CardPasswordUiModel = CardPasswordUiModel(),
    val bankType: BankTypeUiModel = BankTypeUiModel.NOT_SELECTED,
    val isModifyingCard: Boolean = true,
    val shouldOpenBankSelector: Boolean = bankType == BankTypeUiModel.NOT_SELECTED,
) : Parcelable {
    @IgnoredOnParcel
    val canRegisterCard: Boolean =
        cardNumber.isValid &&
            cardExpirationDate.isValid &&
            cardholderName.isValid &&
            cardPassword.isValid &&
            bankType != BankTypeUiModel.NOT_SELECTED &&
            isModifyingCard

    @IgnoredOnParcel
    val cardNumberErrorMessageResId: Int? =
        when (cardNumber.state) {
            CardNumberUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            CardNumberUiModel.State.NOT_FILLED,
            CardNumberUiModel.State.VALID,
            -> null
        }

    @IgnoredOnParcel
    val cardExpirationDateErrorMessageResId: Int? =
        when (cardExpirationDate.state) {
            CardExpirationDateUiModel.State.INVALID_FORMAT -> R.string.card_expiration_date_invalid_date_error_message
            CardExpirationDateUiModel.State.EXPIRED -> R.string.card_expiration_date_expired_error_message
            CardExpirationDateUiModel.State.NOT_FILLED,
            CardExpirationDateUiModel.State.VALID,
            -> null
        }

    @IgnoredOnParcel
    val cardholderNameErrorMessageResId: Int? =
        when (cardholderName.state) {
            CardholderNameUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            CardholderNameUiModel.State.VALID -> null
        }

    @IgnoredOnParcel
    val cardPasswordErrorMessageResId: Int? =
        when (cardPassword.state) {
            CardPasswordUiModel.State.INVALID -> R.string.common_invalid_format_error_message
            CardPasswordUiModel.State.NOT_FILLED,
            CardPasswordUiModel.State.VALID,
            -> null
        }

    fun toPaymentCardUiModel(): PaymentCardUiModel =
        PaymentCardUiModel(
            bankType = bankType,
            number = cardNumber,
            expirationDate = cardExpirationDate,
            cardholderName = cardholderName,
            password = cardPassword,
        )

    companion object {
        fun from(paymentCard: PaymentCardUiModel): CardRegistrationScreenUiState =
            with(paymentCard) {
                CardRegistrationScreenUiState(
                    cardId = id,
                    cardNumber = number,
                    cardExpirationDate = paymentCard.expirationDate,
                    cardholderName = paymentCard.cardholderName,
                    cardPassword = paymentCard.password,
                    bankType = paymentCard.bankType,
                    isModifyingCard = false,
                    shouldOpenBankSelector = false,
                )
            }
    }
}
