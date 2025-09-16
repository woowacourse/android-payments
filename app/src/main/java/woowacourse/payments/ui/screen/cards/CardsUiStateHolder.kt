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
    var uiEvent by mutableStateOf<CardsUiEvent>(CardsUiEvent.None)

    fun update(newCard: CardUiModel?) {
        newCard?.let { newCard ->
            uiState = uiState.addCard(newCard)
            uiEvent = CardsUiEvent.AddCardSuccess
        } ?: run {
            uiEvent = CardsUiEvent.AddCardFailure
        }
    }

    companion object {
        val Saver: Saver<CardsUiStateHolder, CardsUiState> =
            Saver(
                save = { stateHolder -> stateHolder.uiState },
                restore = { uiState -> CardsUiStateHolder(uiState) },
            )
    }
}
