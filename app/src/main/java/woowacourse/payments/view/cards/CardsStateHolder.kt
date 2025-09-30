package woowacourse.payments.view.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardsRepository
import woowacourse.payments.domain.MockCardsRepository
import woowacourse.payments.view.toUiModel
import woowacourse.payments.view.ui.model.CardUiModel

class CardsStateHolder(
    initialState: CardsUiState = CardsUiState(),
    private val repository: CardsRepository = MockCardsRepository,
) {
    var uiState: CardsUiState by mutableStateOf(initialState)
        private set

    fun fetchCards() {
        runCatching {
            repository.cards.map(Card::toUiModel)
        }.onSuccess { cards: List<CardUiModel> ->
            uiState = uiState.copy(cards = cards)
        }
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
