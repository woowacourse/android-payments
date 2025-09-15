package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class CardsUiStateHolder(
    initialState: CardsUiState = CardsUiState.Empty,
) {
    var uiState by mutableStateOf(initialState)

    fun update(newCard: CardUiModel) {
        uiState = uiState.addCard(newCard)
    }

    companion object {
        private const val KEY_EMPTY = "KEY_EMPTY"
        private const val KEY_SINGLE_CARD = "KEY_SINGLE_CARD"
        private const val KEY_MULTIPLE_CARDS = "KEY_MULTIPLE_CARDS"

        val Saver: Saver<CardsUiStateHolder, *> =
            listSaver(
                save = { holder ->
                    when (val state = holder.uiState) {
                        is CardsUiState.Empty -> listOf(KEY_EMPTY)
                        is CardsUiState.SingleCard -> listOf(KEY_SINGLE_CARD, state.card)
                        is CardsUiState.MultipleCards -> listOf(KEY_MULTIPLE_CARDS, state.cards)
                    }
                },
                restore = { saver ->
                    val type = saver.first()
                    val state =
                        when (type) {
                            KEY_EMPTY -> CardsUiState.Empty
                            KEY_SINGLE_CARD -> CardsUiState.SingleCard(saver[1] as CardUiModel)
                            KEY_MULTIPLE_CARDS -> {
                                val cards =
                                    (saver[1] as? List<*>)?.map { it as CardUiModel } ?: emptyList()
                                CardsUiState.MultipleCards(cards)
                            }

                            else -> CardsUiState.Empty
                        }
                    CardsUiStateHolder(state)
                },
            )
    }
}
