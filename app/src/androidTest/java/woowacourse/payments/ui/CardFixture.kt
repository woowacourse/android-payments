package woowacourse.payments.ui

import woowacourse.payments.domain.model.Bank
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.ExpirationDateUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardStateHolder

val HWANNOW_CARD =
    PaymentCardUiModel(
        id = 1,
        bankType = BankType.SHINHAN,
        cardNumber = CardNumberUiModel("1234123412341234"),
        expirationDate = ExpirationDateUiModel("0611"),
        cardHolder = CardHolderUiModel("김환노"),
    )

val JUNSEO511_CARD =
    PaymentCardUiModel(
        id = 2,
        bankType = BankType.SHINHAN,
        cardNumber = CardNumberUiModel("1234123412341234"),
        expirationDate = ExpirationDateUiModel("0511"),
        cardHolder = CardHolderUiModel("김공백"),
    )

fun NewCardStateHolder.updateInitialCard(initialCard: PaymentCardUiModel) {
    this.updateId(initialCard.id)
    this.updateCardNumber(initialCard.cardNumber.value)
    this.updateCardHolder(initialCard.cardHolder.value)
    this.updateBank(Bank(initialCard.bankType))
    this.expirationDateUiState.onValueChanged(initialCard.expirationDate.value)
}
