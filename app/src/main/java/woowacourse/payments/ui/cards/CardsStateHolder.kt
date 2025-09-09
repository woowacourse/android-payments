package woowacourse.payments.ui.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cards.model.CardsUiState

class CardsStateHolderSaver : Saver<CardsStateHolder, CardsUiState> {
    override fun SaverScope.save(value: CardsStateHolder): CardsUiState? =
        value.cardsUiState

    override fun restore(value: CardsUiState): CardsStateHolder? =
        CardsStateHolder(value)
}

class CardsStateHolder(cardsUiState: CardsUiState) {
    var cardsUiState by mutableStateOf(cardsUiState)
        private set
}