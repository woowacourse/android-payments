package woowacourse.payments.ui.screen.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardsUiState : Parcelable {
    data object Empty : CardsUiState

    data class SingleCard(
        val card: CardUiModel,
    ) : CardsUiState

    data class MultipleCards(
        val cards: List<CardUiModel>,
    ) : CardsUiState

    fun addCard(newCard: CardUiModel): CardsUiState =
        when (this) {
            is Empty -> SingleCard(newCard)
            is SingleCard -> MultipleCards(listOf(card, newCard))
            is MultipleCards -> MultipleCards(cards + newCard)
        }

    fun replaceCard(
        oldCard: CardUiModel,
        newCard: CardUiModel,
    ): CardsUiState =
        when (this) {
            is Empty -> this
            is SingleCard -> if (card == oldCard) SingleCard(newCard) else this
            is MultipleCards -> {
                if (oldCard !in this.cards) this
                MultipleCards(
                    cards.map { card -> if (card == oldCard) newCard else card },
                )
            }
        }
}
