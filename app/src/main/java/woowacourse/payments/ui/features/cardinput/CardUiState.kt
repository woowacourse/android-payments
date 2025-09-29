package woowacourse.payments.ui.features.cardinput

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardCompanyUiModel

@Parcelize
data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.UNKNOWN,
) : Parcelable
