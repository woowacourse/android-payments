package woowacourse.payments.ui

import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.ExpirationDateUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

val HWANNOW_CARD =
    PaymentCardUiModel(
        bankType = BankType.SHINHAN,
        cardNumber = CardNumberUiModel("1234123412341234"),
        expirationDate = ExpirationDateUiModel("0611"),
        cardHolder = CardHolderUiModel("김환노"),
    )

val JUNSEO511_CARD =
    PaymentCardUiModel(
        bankType = BankType.SHINHAN,
        cardNumber = CardNumberUiModel("1234123412341234"),
        expirationDate = ExpirationDateUiModel("0511"),
        cardHolder = CardHolderUiModel("김공백"),
    )
