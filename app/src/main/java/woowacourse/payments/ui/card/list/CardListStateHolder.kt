package woowacourse.payments.ui.card.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolder {
    var uiState by mutableStateOf<CardListUiState>(CardListUiState.Empty)
        private set
    private var cards by mutableStateOf(emptyList<CardUiModel>())

    init {
        updateUiState()
    }

    fun contains(id: Long): Boolean {
        return cards.any { it.id == id }
    }

    fun addNewCard(newCard: CardUiModel) {
        cards = cards + newCard
        updateUiState()
    }

    fun updateCard(updatedCard: CardUiModel) {
        cards = cards.map { if (it.id == updatedCard.id) updatedCard else it }
        updateUiState()
    }

    private fun updateUiState() {
        uiState =
            when {
                cards.isEmpty() -> CardListUiState.Empty
                cards.size == 1 -> CardListUiState.Single(cards.first())
                else -> CardListUiState.Multiple(cards)
            }
    }
}
