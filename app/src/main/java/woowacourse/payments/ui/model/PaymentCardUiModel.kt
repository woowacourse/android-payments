package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val dbId: Int,
    val cardCompanyUiModel: CardCompanyUiModel,
    val formattedCardNumber: String,
    val formattedExpireDate: String,
    val ownerName: String,
) : Parcelable {
    companion object {
        const val EMPTY_DB_ID = -1
        const val MAX_EXPIRE_DATE_INPUT_LENGTH = 4
        val EMPTY =
            PaymentCardUiModel(
                dbId = EMPTY_DB_ID,
                cardCompanyUiModel = CardCompanyUiModel.UNKNOWN,
                formattedCardNumber = "",
                formattedExpireDate = "",
                ownerName = "",
            )
    }
}
