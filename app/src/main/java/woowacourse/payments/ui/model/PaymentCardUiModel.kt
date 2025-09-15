package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.BankType

@Parcelize
data class PaymentCardUiModel(
    val bankType: BankType,
    val cardNumber: CardNumberUiModel,
    val cardHolder: CardHolderUiModel,
    val expirationDate: ExpirationDateUiModel,
) : Parcelable
