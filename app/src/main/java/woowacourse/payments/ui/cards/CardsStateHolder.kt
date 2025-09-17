package woowacourse.payments.ui.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cards.model.CardsState
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsStateHolderSaver : Saver<CardsStateHolder, CardsState> {
    override fun SaverScope.save(value: CardsStateHolder): CardsState? = value.cardsState

    override fun restore(value: CardsState): CardsStateHolder? = CardsStateHolder(value)
}

class CardsStateHolder(
    cardsState: CardsState,
) {
    var cardsState by mutableStateOf(cardsState)
        private set

    fun addCard(cardUiModel: PaymentCardUiModel) {
        cardsState = cardsState.addCard(cardUiModel)
    }
}
