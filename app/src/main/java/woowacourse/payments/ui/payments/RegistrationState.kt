package woowacourse.payments.ui.payments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed class RegistrationState: Parcelable {
    data object Add : RegistrationState()

    data class Modify(val paymentCardUiModel: PaymentCardUiModel): RegistrationState()
}