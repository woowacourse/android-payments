package woowacourse.payments.ui.screen.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardsUiState : Parcelable {
    fun addCard(newCard: CardUiModel): CardsUiState =
        when (this) {
            is Empty -> SingleCard(newCard)
            is SingleCard -> MultipleCards(listOf(card, newCard))
            is MultipleCards -> MultipleCards(cards + newCard)
        }

    data object Empty : CardsUiState

    data class SingleCard(
        val card: CardUiModel,
    ) : CardsUiState

    data class MultipleCards(
        val cards: List<CardUiModel>,
    ) : CardsUiState
}
