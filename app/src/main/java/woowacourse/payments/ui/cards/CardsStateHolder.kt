package woowacourse.payments.ui.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsStateHolderSaver : Saver<CardsStateHolder, CardsUiState> {
    override fun SaverScope.save(value: CardsStateHolder): CardsUiState? =
        value.cardsUiState

    override fun restore(value: CardsUiState): CardsStateHolder? =
        CardsStateHolder(value)
}

class CardsStateHolder(cardsUiState: CardsUiState) {
    var cardsUiState by mutableStateOf(cardsUiState)
        private set

    fun addCard(cardUiModel: PaymentCardUiModel) {
        val currentCardsUiState = cardsUiState
        cardsUiState = when (currentCardsUiState) {
            is CardsUiState.Multiple -> CardsUiState.Multiple(currentCardsUiState.cards + cardUiModel)
            CardsUiState.None -> CardsUiState.Single(cardUiModel)
            is CardsUiState.Single -> CardsUiState.Multiple(
                listOf(
                    currentCardsUiState.card,
                    cardUiModel
                )
            )
        }
    }
}