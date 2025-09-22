package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val cardCompanyUiModel: CardCompanyUiModel,
    val formattedCardNumber: String,
    val formattedExpireDate: String,
    val ownerName: String,
) : Parcelable {
    companion object {
        const val MAX_EXPIRE_DATE_INPUT_LENGTH = 4
        val EMPTY =
            PaymentCardUiModel(
                cardCompanyUiModel = CardCompanyUiModel.UNKNOWN,
                formattedCardNumber = "",
                formattedExpireDate = "",
                ownerName = "",
            )
    }
}
