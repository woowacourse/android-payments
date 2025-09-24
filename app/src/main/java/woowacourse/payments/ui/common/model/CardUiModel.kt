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
    val password: String,
) : Parcelable

fun NewCardUiState.toUiModel(): CardUiModel? =
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

fun CardUiModel.toUiState(): NewCardUiState =
    NewCardUiState(
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
