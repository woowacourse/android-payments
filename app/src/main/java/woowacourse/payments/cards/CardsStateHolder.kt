package woowacourse.payments.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
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
}

@Composable
fun rememberCardsStateHolder(initialState: CardsUiState = CardsUiState()): CardsStateHolder =
    rememberSaveable(
        saver =
            Saver(
                save = { stateHolder: CardsStateHolder -> stateHolder.uiState },
                restore = { uiState: CardsUiState -> CardsStateHolder(uiState) },
            ),
    ) { CardsStateHolder(initialState) }
