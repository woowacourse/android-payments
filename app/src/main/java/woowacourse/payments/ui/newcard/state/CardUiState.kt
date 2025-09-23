package woowacourse.payments.ui.newcard.state

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class CardUiState(
    val card: Card? = null,

    val cardCompany: CardCompany? = null,
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",

    val cardErrorMessage: String? = null,
    val numberErrorMessage: String? = null,
    val expirationDateErrorMessage: String? = null,
    val ownerNameErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,

    val newCardStatus: NewCardStatus = NewCardStatus.CreateCard,

    val isBottomSheetOpen: Boolean = true,

    val isChangeCardCompany: Boolean = false,
    val isChangeNumber: Boolean = false,
    val isChangeExpirationDate: Boolean = false,
    val isChangeOwnerName: Boolean = false,
    val isPassword: Boolean = false,
) {
    val cardCompanyUiModel: CardCompanyUiModel
        get() = cardCompany?.toUiModel() ?: CardCompanyUiModel.Default

    val cardUiModel: CardUiModel
        get() = CardUiModel(
            cardCompanyUiModel = cardCompanyUiModel,
            number = number,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password
        )

    val isPossibleAddCard: Boolean =
        if (cardCompany != null && number.length == 16 && expiredDate.length == 4 && password.length == 4) true else false

}