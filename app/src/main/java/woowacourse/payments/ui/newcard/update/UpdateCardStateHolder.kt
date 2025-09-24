package woowacourse.payments.ui.newcard.update

import woowacourse.payments.ui.model.CardUiModel


class UpdateCardStateHolder(
) {
    fun isCardUpdated(currentCard: CardUiModel, updatedCard: CardUiModel) =
        currentCard != updatedCard
} 