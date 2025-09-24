package woowacourse.payments.ui.cards.model

import woowacourse.payments.ui.model.CardUiModel

sealed interface CardsUiState {
    data class Success(val content: Content) : CardsUiState {
        sealed interface Content {
            data object None : Content

            data class Single(
                val card: CardUiModel,
            ) : Content

            data class Multiple(val cards: List<CardUiModel>) : Content

            companion object {
                fun of(paymentCards: List<CardUiModel>): Content =
                    when (paymentCards.size) {
                        0 -> None
                        1 -> Single(paymentCards.first())
                        else -> Multiple(paymentCards)
                    }
            }
        }
    }
}
