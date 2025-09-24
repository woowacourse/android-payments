package woowacourse.payments.ui.cards.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardsState : Parcelable {
    fun addCard(card: CardUiModel): CardsState

    data object None : CardsState {
        override fun addCard(card: CardUiModel) = Single(card)
    }

    data class Single(
        val card: CardUiModel,
    ) : CardsState {
        override fun addCard(card: CardUiModel): CardsState =
            if (this.card.id == card.id) Single(card) else Multiple(listOf(this.card, card))
    }

    data class Multiple(
        val cards: List<CardUiModel>,
    ) : CardsState {
        override fun addCard(card: CardUiModel): CardsState =
            if (cards.any { it.id == card.id }) {
                copy(cards = cards.map { if (it.id == card.id) card else it })
            } else {
                copy(cards = cards + card)
            }
    }

    companion object {
        fun of(paymentCards: List<CardUiModel>): CardsState =
            when (paymentCards.size) {
                0 -> None
                1 -> Single(paymentCards.first())
                else -> Multiple(paymentCards)
            }
    }
}
