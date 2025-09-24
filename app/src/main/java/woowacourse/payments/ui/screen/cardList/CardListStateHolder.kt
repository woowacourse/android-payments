package woowacourse.payments.ui.screen.cardList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolder(
    initialCards: List<CardUiModel> = emptyList(),
) {
    private var nextId = (initialCards.maxOfOrNull { it.id } ?: 0L) + 1L
    var uiState by mutableStateOf(
        CardListUiState(
            cards = initialCards,
            showAddButton = initialCards.size > 1,
        ),
    )
        private set

    fun upsertCard(card: CardUiModel): Boolean {
        val index = uiState.cards.indexOfFirst { it.id == card.id && card.id != 0L }
        val isUpdate = index >= 0

        val newCards =
            if (index >= 0) {
                // 기존 카드 수정
                uiState.cards.toMutableList().apply { this[index] = card }
            } else {
                // 새 카드 추가
                val cardWithNewId = card.copy(id = nextId++)
                uiState.cards + cardWithNewId
            }

        uiState =
            uiState.copy(
                cards = newCards,
                showAddButton = newCards.size > 1,
            )

        return isUpdate
    }

    companion object {
        val saver =
            Saver<CardListStateHolder, List<CardUiModel>>(
                save = { holder -> holder.uiState.cards },
                restore = { cards -> CardListStateHolder(cards) },
            )
    }
}
