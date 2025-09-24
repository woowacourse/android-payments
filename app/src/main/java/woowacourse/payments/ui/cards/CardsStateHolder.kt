package woowacourse.payments.ui.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cards.model.CardsUiEvent
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.cards.model.CardsUiState.*
import woowacourse.payments.ui.model.CardUiModel

class CardsStateHolderSaver : Saver<CardsStateHolder, List<CardUiModel>> {
    override fun SaverScope.save(value: CardsStateHolder): List<CardUiModel>? {
        when (val uiState = value.cardsUiState) {
            is Success -> {
                return when (val content = uiState.content) {
                    is Success.Content.Multiple -> content.cards
                    Success.Content.None -> emptyList()
                    is Success.Content.Single -> listOf(content.card)
                }
            }
        }
    }

    override fun restore(value: List<CardUiModel>): CardsStateHolder? = CardsStateHolder(value)
}

class CardsStateHolder(
    val cards: List<CardUiModel>
) {
    var cardsUiState: CardsUiState by mutableStateOf(loadCardsUiState(cards))
        private set

    var cardsUiEvent: CardsUiEvent? by mutableStateOf(null)
        private set

    private fun loadCardsUiState(cards: List<CardUiModel>) =
        Success(Success.Content.of(cards))

    fun upsertCard(cardUiModel: CardUiModel) {
        when (val current = cardsUiState) {
            is Success -> {
                when (current.content) {
                    is Success.Content.Multiple -> if (current.content.cards.any { it.id == cardUiModel.id }) updateCard(
                        cardUiModel
                    ) else addCard(cardUiModel)

                    Success.Content.None -> addCard(cardUiModel)
                    is Success.Content.Single -> if (current.content.card.id == cardUiModel.id) updateCard(
                        cardUiModel
                    ) else addCard(cardUiModel)
                }
            }
        }
    }

    private fun addCard(cardUiModel: CardUiModel) {
        cardsUiState = when (val current = cardsUiState) {
            is Success -> {
                when (current.content) {
                    is Success.Content.Multiple ->
                        current.copy(current.content.copy(current.content.cards + cardUiModel))

                    Success.Content.None -> current.copy(Success.Content.Single(cardUiModel))
                    is Success.Content.Single ->
                        current.copy(
                            Success.Content.Multiple(
                                listOf(
                                    current.content.card,
                                    cardUiModel
                                )
                            )
                        )
                }
            }
        }
        cardsUiEvent = CardsUiEvent.AddCard
    }

    private fun updateCard(cardUiModel: CardUiModel) {
        cardsUiState = when (val current = cardsUiState) {
            is Success -> {
                when (current.content) {
                    is Success.Content.Multiple -> current.copy(current.content.copy(current.content.cards.map { card -> if (card.id == cardUiModel.id) cardUiModel else card }))
                    Success.Content.None -> current
                    is Success.Content.Single -> current.copy(Success.Content.Single(cardUiModel))
                }
            }
        }
        cardsUiEvent = CardsUiEvent.UpdateCard
    }
}
