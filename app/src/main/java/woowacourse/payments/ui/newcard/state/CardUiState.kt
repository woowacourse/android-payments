package woowacourse.payments.ui.newcard.state

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

data class CardUiState(
    val card: Card? = null,

    val cardCompany: CardCompany? = null,
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",

    val cardErrorMessage: String? = null,
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