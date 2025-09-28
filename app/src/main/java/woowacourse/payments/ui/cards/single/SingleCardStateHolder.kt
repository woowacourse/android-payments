package woowacourse.payments.ui.cards.single

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class SingleCardStateHolderSaver : Saver<SingleCardStateHolder, CardUiModel> {
    override fun SaverScope.save(value: SingleCardStateHolder): CardUiModel? = value.card

    override fun restore(value: CardUiModel): SingleCardStateHolder? = SingleCardStateHolder(value)
}

class SingleCardStateHolder(
    initCard: CardUiModel
) {
    var card: CardUiModel by mutableStateOf(initCard)
        private set

    var uiEvent: SingleCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard(cardUiModel: CardUiModel) {
        uiEvent = SingleCardsUiEvent.AddCard(listOf(card, cardUiModel))
    }

    fun updateCard(cardUiModel: CardUiModel) {
        card = cardUiModel
        uiEvent = SingleCardsUiEvent.UpdateCard
    }
}
