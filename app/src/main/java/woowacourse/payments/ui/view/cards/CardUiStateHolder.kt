package woowacourse.payments.ui.view.cards

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.serialization.toSerializationCard

class CardUiStateHolder(
    initialState: CardsUiState = CardsUiState.EMPTY,
) {
    private val _uiState = mutableStateOf(initialState)
    val uiState: CardsUiState get() = _uiState.value

    val toolbarActionButtonVisibility: Boolean
        get() = uiState is CardsUiState.MULTIPLE

    fun addCard(newCard: SerializationCard) {
        val card = newCard.toDomain()
        _uiState.value =
            when (val currentState = uiState) {
                is CardsUiState.EMPTY -> CardsUiState.SINGLE(card)
                is CardsUiState.SINGLE -> CardsUiState.MULTIPLE(listOf(currentState.state, card))
                is CardsUiState.MULTIPLE -> CardsUiState.MULTIPLE(currentState.state + card)
            }
    }

    companion object {
        val Saver: Saver<CardUiStateHolder, Any> =
            listSaver(
                save = { holder ->
                    when (val uiState = holder.uiState) {
                        is CardsUiState.EMPTY ->
                            listOf(CardUiStateType.EMPTY)

                        is CardsUiState.SINGLE ->
                            listOf(CardUiStateType.SINGLE, uiState.state.toSerializationCard())

                        is CardsUiState.MULTIPLE ->
                            listOf(CardUiStateType.MULTIPLE) + uiState.state.map { it.toSerializationCard() }
                    }
                },
                restore = { list ->
                    when (list.first() as CardUiStateType) {
                        CardUiStateType.EMPTY ->
                            CardUiStateHolder(CardsUiState.EMPTY)

                        CardUiStateType.SINGLE -> {
                            val card = list[1] as SerializationCard
                            CardUiStateHolder(CardsUiState.SINGLE(card.toDomain()))
                        }

                        CardUiStateType.MULTIPLE -> {
                            val cards = list.drop(1).map { it as SerializationCard }
                            CardUiStateHolder(CardsUiState.MULTIPLE(cards.map { it.toDomain() }))
                        }
                    }
                },
            )
    }

    enum class CardUiStateType {
        EMPTY,
        SINGLE,
        MULTIPLE,
    }
}
