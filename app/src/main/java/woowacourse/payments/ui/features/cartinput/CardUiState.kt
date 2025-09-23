package woowacourse.payments.ui.features.cartinput

import woowacourse.payments.ui.model.CardCompanyUiModel

data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.UNKNOWN,
)
