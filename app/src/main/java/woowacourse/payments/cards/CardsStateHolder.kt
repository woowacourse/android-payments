package woowacourse.payments.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.Card

class CardsStateHolder(
    initialState: CardsUiState = CardsUiState(),
) {
    var uiState: CardsUiState by mutableStateOf(initialState)
        private set

    fun addCard(card: Card) {
        uiState = uiState.copy(cards = uiState.cards + card)
    }

    companion object {
        val Saver: Saver<CardsStateHolder, List<Card>> =
            Saver(
                save = { stateHolder: CardsStateHolder -> stateHolder.uiState.cards },
                restore = { cards: List<Card> -> CardsStateHolder(CardsUiState(cards)) },
            )
    }
}
