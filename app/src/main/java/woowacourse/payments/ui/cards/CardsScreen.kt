package woowacourse.payments.ui.cards

import woowacourse.payments.ui.model.CardUiModel

sealed interface CardsScreen {
    data object Non : CardsScreen
    data class Single(val card: CardUiModel) : CardsScreen
    data class Multi(val cards: List<CardUiModel>) : CardsScreen
}
