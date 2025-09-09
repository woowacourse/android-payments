package woowacourse.payments.ui.cards.model

import woowacourse.payments.ui.model.PaymentCardUiModel

sealed interface CardsUiState {
    data object None : CardsUiState
    data class Single(val card: PaymentCardUiModel) : CardsUiState
    data class Multiple(val cards: List<PaymentCardUiModel>) : CardsUiState

    companion object {
        fun of(paymentCards: List<PaymentCardUiModel>): CardsUiState =
            when (paymentCards.size) {
                0 -> None
                1 -> Single(paymentCards.first())
                else -> Multiple(paymentCards)
            }
    }
}