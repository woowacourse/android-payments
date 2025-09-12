package woowacourse.payments.ui.screen.cardList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolder(
    initialState: CardListUiState = CardListUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun addCard(card: CardUiModel) {
        uiState = uiState.copy(cards = uiState.cards + card)
    }

    companion object {
        val saver =
            Saver<CardListStateHolder, List<CardUiModel>>(
                save = { holder -> holder.uiState.cards },
                restore = { cards -> CardListStateHolder(CardListUiState(cards)) },
            )
    }
}
