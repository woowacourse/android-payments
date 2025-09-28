package woowacourse.payments.ui.cards

import woowacourse.payments.domain.Card

sealed interface CardsScreen {
    data object Non : CardsScreen

    data class Single(
        val cardId: Long,
    ) : CardsScreen

    data class Multi(
        val cardIds: List<Long>,
    ) : CardsScreen

    companion object {
        fun of(cards: List<Card>) =
            when (cards.size) {
                0 -> Non
                1 -> Single(cards.first().id)
                else -> Multi(cards.map { it.id })
            }
    }
}
