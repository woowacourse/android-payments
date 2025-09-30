package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.cardupdate.CardUpdateUiState
import woowacourse.payments.ui.cardupdate.model.CardCompanyUiModel

@Parcelize
data class CardUiModel(
    val cardCompany: CardCompanyUiModel,
    val number: String,
    val expirationDate: String,
    val holderName: String,
    val password: String,
) : Parcelable

fun CardUpdateUiState.toUiModel(): CardUiModel? =
    cardCompany
        ?.let { company: CardCompanyUiModel ->
            CardUiModel(
                cardCompany = company,
                number = cardNumber,
                expirationDate = cardExpirationDate,
                holderName = cardHolderName.trim(),
                password = cardPassword,
            )
        }

fun CardUiModel.toUiState(): CardUpdateUiState =
    CardUpdateUiState(
        cardCompany = cardCompany,
        cardNumber = number,
        isCardNumberValid = true,
        cardExpirationDate = expirationDate,
        isCardExpirationDateValid = true,
        cardHolderName = holderName,
        isCardHolderNameValid = true,
        cardPassword = password,
        isCardPasswordValid = true,
    )
