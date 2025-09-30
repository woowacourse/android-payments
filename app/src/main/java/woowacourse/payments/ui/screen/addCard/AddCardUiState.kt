package woowacourse.payments.ui.screen.addCard

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class AddCardUiState(
    val cardNumber: String = "",
    val expired: String = "",
    val cardOwner: String = "",
    val password: String = "",
    val cardCompanySelectionState: CardCompanySelectionState = CardCompanySelectionState.NotSelected,
    val errors: Set<AddCardError> = emptySet(),
    val submitted: Boolean = false,
) : Parcelable {
    @IgnoredOnParcel
    val cardNumberError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.CARD_NUMBER_INVALID } else null

    @IgnoredOnParcel
    val expiredError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.EXPIRED_INVALID } else null

    @IgnoredOnParcel
    val ownerError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.OWNER_INVALID } else null

    @IgnoredOnParcel
    val passwordError: AddCardError? =
        if (submitted) errors.find { it == AddCardError.PASSWORD_INVALID } else null
}

fun AddCardUiState.toCardUiModel(): CardUiModel =
    CardUiModel(
        id = 0L,
        bank =
            when (cardCompanySelectionState) {
                is CardCompanySelectionState.NotSelected -> BankUiModel.Companion.NOT_SELECTED
                is CardCompanySelectionState.Selected -> cardCompanySelectionState.bank
            },
        number = cardNumber,
        expired = expired,
        owner = cardOwner,
    )

fun CardUiModel.toAddCardUiState(): AddCardUiState =
    AddCardUiState(
        cardNumber = this.number,
        expired = this.expired,
        cardOwner = this.owner,
        password = "****",
        cardCompanySelectionState = CardCompanySelectionState.Selected(bank),
    )
