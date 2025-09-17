package woowacourse.payments.ui.registration.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val isBottomSheetOpen: Boolean = true,
    val cardNumber: CardNumberUiModel = CardNumberUiModel(),
    val cardExpirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val cardExpirationDateErrorMessage: String? = null,
    val cardholderName: CardholderNameUiModel = CardholderNameUiModel(),
    val cardPassword: CardPasswordUiModel = CardPasswordUiModel(),
    val cardCompany: CardCompanyUiModel = CardCompanyUiModel.NOT_SELECT,
) : Parcelable {
    val isRegistrableCard: Boolean
        get() = (
            !cardNumber.isError && !cardExpirationDate.isError && !cardPassword.isError &&
                cardCompany.isSelect()
        )
}

fun CardRegistrationScreenUiState.toCardUiModel(): CardUiModel =
    CardUiModel(
        cardCompanyUiModel = cardCompany,
        cardholderNameUiModel = cardholderName,
        cardNumberUiModel = cardNumber,
        cardExpirationDateUiModel = cardExpirationDate,
    )
