package woowacourse.payments.ui.screen.cardList

import woowacourse.payments.ui.model.CardUiModel

data class CardListUiState(
    val cards: List<CardUiModel> = emptyList(),
) {
    val showAddButton: Boolean get() = cards.size > 1
    val enableScroll: Boolean get() = cards.size > 3
}
