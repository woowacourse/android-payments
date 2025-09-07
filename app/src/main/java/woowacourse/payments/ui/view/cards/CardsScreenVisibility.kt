package woowacourse.payments.ui.view.cards

import woowacourse.payments.domain.Card

enum class CardsScreenVisibility {
    EMPTY,
    SINGLE,
    MULTIPLE;

    companion object {
        fun of(cards: List<Card>): CardsScreenVisibility = when {
            cards.isEmpty() -> EMPTY
            cards.size == 1 -> SINGLE
            cards.size > 1 -> MULTIPLE
            else -> throw IllegalStateException(INVALID_CARD_LIST_SIZE_MESSAGE.format(cards.size))
        }

        private const val INVALID_CARD_LIST_SIZE_MESSAGE = "카드의 사이즈가 잘못되었습니다 %d"
    }
}

