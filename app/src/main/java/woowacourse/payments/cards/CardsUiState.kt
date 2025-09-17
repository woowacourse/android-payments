package woowacourse.payments.cards

import woowacourse.payments.Card

data class CardsUiState(
    val cards: List<Card> = emptyList(),
)
