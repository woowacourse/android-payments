package woowacourse.payments.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.Card
import woowacourse.payments.CardUiModel
import woowacourse.payments.CardsRepository
import woowacourse.payments.MockCardsRepository
import woowacourse.payments.toDomain
import woowacourse.payments.toUiModel

class CardsStateHolder(
    initialState: CardsUiState = CardsUiState(),
    private val repository: CardsRepository = MockCardsRepository,
) {
    var uiState: CardsUiState by mutableStateOf(initialState)
        private set
    var event by mutableStateOf<CardsUiEvent?>(null)
        private set

    fun fetchCards() {
        runCatching {
            repository.cards.map(Card::toUiModel)
        }.onSuccess { cards: List<CardUiModel> ->
            uiState = uiState.copy(cards = cards)
        }
    }

    fun clearEvent() {
        event = null
    }

    fun addCard(card: CardUiModel) {
        runCatching {
            repository.addCard(card.toDomain())
        }.onSuccess {
            event = CardsUiEvent.AddCardSuccess
        }
    }

    fun editCard(
        old: CardUiModel,
        new: CardUiModel,
    ) {
        runCatching {
            repository.editCard(old.toDomain(), new.toDomain())
        }.onSuccess {
            event = CardsUiEvent.EditCardSuccess
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
