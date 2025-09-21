package woowacourse.payments.ui.view.cards

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
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
        private const val KEY_CARD = "card"

        val Saver: Saver<CardUiStateHolder, Any> =
            mapSaver(
                save = { holder ->
                    when (val currentUiState = holder.uiState) {
                        is CardsUiState.EMPTY -> emptyMap<String, Any>()
                        is CardsUiState.SINGLE ->
                            mapOf(
                                KEY_CARD to currentUiState.state.toSerializationCard(),
                            )
                        is CardsUiState.MULTIPLE ->
                            mapOf(
                                KEY_CARD to currentUiState.state.map { it.toSerializationCard() },
                            )
                    }
                },
                restore = { restored ->
                    val map = restored[KEY_CARD]
                    when (map) {
                        is SerializationCard ->
                            CardUiStateHolder(CardsUiState.SINGLE(map.toDomain()))

                        is List<*> ->
                            CardUiStateHolder(
                                CardsUiState.MULTIPLE(
                                    map.filterIsInstance<SerializationCard>().map { it.toDomain() },
                                ),
                            )

                        else -> CardUiStateHolder(CardsUiState.EMPTY)
                    }
                },
            )
    }
}
