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
        CardStorage.add(card)
        updateUiState()
    }

    fun updateCard(
        cardId: Long,
        updateCard: CardUiModel,
    ) {
        val card = updateCard.toDomain()
        CardStorage.update(cardId, card)
        updateUiState()
    }

    private fun updateUiState() {
        val cards = CardStorage.findAll()
        val cardUiModels = cards.map { it.toUiModel() }

        _uiState.value =
            when {
                cards.isEmpty() -> CardsUiState.Empty
                cards.size == 1 -> CardsUiState.Single(cardUiModels[0])
                else -> CardsUiState.Multiple(cardUiModels)
            }
    }
}
