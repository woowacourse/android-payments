package woowacourse.payments

import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

val CARD_FIXTURE: CardUiModel =
    CardUiModel(
        id = 0,
        cardNumber = "1234123412341234",
        expirationDate = "1299",
        cardholderName = "디랙",
        passcode = "1234",
        cardCompany = CardCompany.KB_CARD.toUiModel(),
    )
