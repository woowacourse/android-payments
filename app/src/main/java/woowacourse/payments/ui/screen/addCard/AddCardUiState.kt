package woowacourse.payments.ui.screen.addCard

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toPresentation

@Parcelize
data class AddCardUiState(
    val cardNumber: String = "",
    val expired: String = "",
    val cardOwner: String = "",
    val password: String = "",
    val bankUiModel: BankUiModel = BankType.NOT_SELECTED.toPresentation(),
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
        bankUiModel = bankUiModel,
        number = cardNumber,
        expired = expired,
        owner = cardOwner,
    )
