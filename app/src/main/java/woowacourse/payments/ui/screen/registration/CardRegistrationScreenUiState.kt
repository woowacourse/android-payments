package woowacourse.payments.ui.screen.registration

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.PaymentCardValidator.PaymentCardValidationResult

@Parcelize
data class CardRegistrationScreenUiState(
    val cardNumber: String = "",
    val cardNumberValidationResult: PaymentCardValidationResult = PaymentCardValidationResult.NOT_FILLED,
    val cardExpirationDate: String = "",
    val cardExpirationDateValidationResult: PaymentCardValidationResult = PaymentCardValidationResult.NOT_FILLED,
    val cardholderName: String = "",
    val cardholderNameValidationResult: PaymentCardValidationResult = PaymentCardValidationResult.NOT_FILLED,
    val cardPassword: String = "",
    val cardPasswordValidationResult: PaymentCardValidationResult = PaymentCardValidationResult.NOT_FILLED,
) : Parcelable {
    @IgnoredOnParcel
    val isSaveButtonEnabled: Boolean =
        listOf(
            cardNumberValidationResult,
            cardExpirationDateValidationResult,
            cardholderNameValidationResult,
            cardPasswordValidationResult,
        ).all { it == PaymentCardValidationResult.VALID }
}
