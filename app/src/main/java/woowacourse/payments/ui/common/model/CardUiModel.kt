package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.NewCardUiState
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Parcelize
data class CardUiModel(
    val companyName: String,
    val color: Long,
    val number: String,
    val expirationDate: String,
    val holderName: String,
) : Parcelable

fun NewCardUiState.toUiModel(): CardUiModel? =
    cardCompany
        ?.let { company: CardCompanyUiModel ->
            CardUiModel(
                companyName = company.name,
                number = cardNumber,
                expirationDate = cardExpirationDate,
                holderName = cardHolderName.trim(),
                color = company.color,
            )
        }
