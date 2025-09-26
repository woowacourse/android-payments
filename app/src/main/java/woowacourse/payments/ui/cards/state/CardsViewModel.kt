package woowacourse.payments.ui.cards.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import toDomain
import toUiModel
import woowacourse.payments.data.CardStorage
import woowacourse.payments.ui.model.CardUiModel

class CardsViewModel(
    initialState: CardsUiState = CardsUiState.Empty,
) {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<CardsUiState> = _uiState.asStateFlow()

    init {
        updateUiState()
    }

    fun registrationCard(cardUiModel: CardUiModel) {
        val card = cardUiModel.toDomain()
        CardStorage.addCard(card)
        updateUiState()
    }

    private fun updateUiState() {
        val cards = CardStorage.cards.map { it.value.toUiModel() }.toList()
        _uiState.value =
            when {
                cards.isEmpty() -> CardsUiState.Empty
                cards.size == 1 -> CardsUiState.Single(cards[0])
                else -> CardsUiState.Multiple(cards)
            }
    }
}
