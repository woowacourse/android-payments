package woowacourse.payments.ui.common.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.NewCardUiState
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Parcelize
data class CardUiModel(
    @StringRes val companyName: Int,
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
