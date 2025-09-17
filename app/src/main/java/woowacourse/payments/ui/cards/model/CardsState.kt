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
            Multiple(listOf(this.card, card))
    }

    data class Multiple(
        val cards: List<PaymentCardUiModel>,
    ) : CardsState {
        override fun addCard(card: PaymentCardUiModel): CardsState = this.copy(this.cards + card)
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
