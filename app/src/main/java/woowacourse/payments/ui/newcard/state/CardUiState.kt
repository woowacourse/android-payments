package woowacourse.payments.ui.newcard.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardUiState(
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.Default,
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",

    val cardErrorMessage: String? = null,
) : Parcelable {
    val cardUiModel: CardUiModel
        get() = CardUiModel(
            cardCompanyUiModel = cardCompanyUiModel,
            number = number,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password
        )

    val isPossibleAddCard: Boolean =
        cardCompanyUiModel != CardCompanyUiModel.Default && number.length == 16 && expiredDate.length == 4 && password.length == 4

}