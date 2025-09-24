package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardsUiStateHolder(
    initialState: CardsUiState = CardsUiState.Empty,
) {
    var uiState by mutableStateOf(initialState)
        private set
    var uiEvent by mutableStateOf<CardsUiEvent>(CardsUiEvent.None)
        private set
    var cardToEdit: CardUiModel? = null
        private set

    fun update(newCard: CardUiModel?) {
        newCard?.let { newCard ->
            uiState = uiState.addCard(newCard)
            uiEvent = CardsUiEvent.AddCardSuccess
        } ?: run {
            uiEvent = CardsUiEvent.AddCardFailure
        }
    }

    fun replaceCard(newCard: CardUiModel?) {
        if (newCard == null) {
            uiEvent = CardsUiEvent.EditCardFailure
            cardToEdit = null
            return
        }
        val oldCard =
            cardToEdit ?: run {
                uiEvent = CardsUiEvent.EditCardFailure
                return
            }
        uiState = uiState.replaceCard(oldCard, newCard)
        uiEvent = CardsUiEvent.EditCardSuccess
        cardToEdit = null
    }

    fun markEditCard(card: CardUiModel) {
        cardToEdit = card
    }

    companion object {
        val Saver: Saver<CardsUiStateHolder, CardsUiState> =
            Saver(
                save = { stateHolder -> stateHolder.uiState },
                restore = { uiState -> CardsUiStateHolder(uiState) },
            )
    }
}
