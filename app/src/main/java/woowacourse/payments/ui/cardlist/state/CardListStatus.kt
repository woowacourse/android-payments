package woowacourse.payments.ui.cardlist.state

import woowacourse.payments.ui.model.CardUiModel

sealed interface CardListUiStatus {
    data object EmptyCardList : CardListUiStatus
    data class OneCardList(val card: CardUiModel) : CardListUiStatus
    data class MultiCardList(val card: List<CardUiModel>) : CardListUiStatus
}