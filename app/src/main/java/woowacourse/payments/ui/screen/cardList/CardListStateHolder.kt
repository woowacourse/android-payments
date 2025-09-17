package woowacourse.payments.ui.screen.cardList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolder(
    initialCards: List<CardUiModel> = emptyList(),
) {
    var uiState by mutableStateOf(
        CardListUiState(
            cards = initialCards,
            showAddButton = initialCards.size > 1,
        ),
    )
        private set

    fun addCard(card: CardUiModel) {
        val newCards = uiState.cards + card
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
                restore = { cards -> CardListStateHolder() },
            )
    }
}
