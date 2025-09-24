package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.NewCardUiState
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Parcelize
data class CardUiModel(
    val cardCompany: CardCompanyUiModel,
    val number: String,
    val expirationDate: String,
    val holderName: String,
) : Parcelable

fun NewCardUiState.toUiModel(): CardUiModel? =
    cardCompany
        ?.let { company: CardCompanyUiModel ->
            CardUiModel(
                cardCompany = company,
                number = cardNumber,
                expirationDate = cardExpirationDate,
                holderName = cardHolderName.trim(),
            )
        }
