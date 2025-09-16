package woowacourse.payments.ui.cards.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed interface CardsUiState : Parcelable {

    fun addCard(card: PaymentCardUiModel): CardsUiState

    data object None : CardsUiState {

        override fun addCard(card: PaymentCardUiModel) = Single(card)
    }

    data class Single(
        val card: PaymentCardUiModel,
    ) : CardsUiState {
        override fun addCard(card: PaymentCardUiModel): CardsUiState =
            Multiple(listOf(this.card, card))
    }

    data class Multiple(
        val cards: List<PaymentCardUiModel>,
    ) : CardsUiState {
        override fun addCard(card: PaymentCardUiModel): CardsUiState = this.copy(this.cards + card)
    }

    companion object {
        fun of(paymentCards: List<PaymentCardUiModel>): CardsUiState =
            when (paymentCards.size) {
                0 -> None
                1 -> Single(paymentCards.first())
                else -> Multiple(paymentCards)
            }
    }
}
