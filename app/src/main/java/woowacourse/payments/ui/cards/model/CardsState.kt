package woowacourse.payments.ui.cards.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed interface CardsState : Parcelable {
    fun addCard(card: PaymentCardUiModel): CardsState

    data object None : CardsState {
        override fun addCard(card: PaymentCardUiModel) = Single(card)
    }

    data class Single(
        val card: PaymentCardUiModel,
    ) : CardsState {
        override fun addCard(card: PaymentCardUiModel): CardsState =
            if (this.card.id == card.id) Single(card) else Multiple(listOf(this.card, card))
    }

    data class Multiple(
        val cards: List<PaymentCardUiModel>,
    ) : CardsState {
        override fun addCard(card: PaymentCardUiModel): CardsState =
            if (cards.any { it.id == card.id }) {
                copy(cards = cards.map { if (it.id == card.id) card else it })
            } else {
                copy(cards = cards + card)
            }
    }

    companion object {
        fun of(paymentCards: List<PaymentCardUiModel>): CardsState =
            when (paymentCards.size) {
                0 -> None
                1 -> Single(paymentCards.first())
                else -> Multiple(paymentCards)
            }
    }
}
