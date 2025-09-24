package woowacourse.payments.ui.newcard.update

import woowacourse.payments.ui.model.PaymentCardUiModel


class UpdateCardStateHolder(
) {
    fun isCardUpdated(currentCard: PaymentCardUiModel, updatedCard: PaymentCardUiModel) =
        currentCard != updatedCard
} 