package woowacourse.payments.ui.screen.cardList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolder(
    initialCards: List<CardUiModel> = emptyList(),
) {
    private var nextId = 0L
    var uiState by mutableStateOf(
        CardListUiState(
            cards = initialCards,
            showAddButton = initialCards.size > 1,
        ),
    )
        private set

    fun upsertCard(card: CardUiModel) {
        val index = uiState.cards.indexOfFirst { it.id == card.id }
        val newCards =
            if (index >= 0) {
                uiState.cards.toMutableList().apply { this[index] = card }
            } else {
                uiState.cards + card.copy(id = nextId++)
            }

        uiState =
            uiState.copy(
                cards = newCards,
                showAddButton = newCards.size > 1,
            )
    }

    companion object {
        val saver =
            Saver<CardListStateHolder, List<CardUiModel>>(
                save = { holder -> holder.uiState.cards },
                restore = { cards -> CardListStateHolder(cards) },
            )
    }
}
