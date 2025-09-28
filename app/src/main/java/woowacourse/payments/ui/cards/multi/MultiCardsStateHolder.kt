package woowacourse.payments.ui.cards.multi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class MultiCardsStateHolderSaver : Saver<MultiCardsStateHolder, List<CardUiModel>> {
    override fun SaverScope.save(value: MultiCardsStateHolder): List<CardUiModel>? = value.cards

    override fun restore(value: List<CardUiModel>): MultiCardsStateHolder? =
        MultiCardsStateHolder(value)
}

class MultiCardsStateHolder(
    initCards: List<CardUiModel>
) {
    var cards: List<CardUiModel> by mutableStateOf(initCards)
        private set

    var uiEvent: MultiCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard(cardUiModel: CardUiModel) {
        cards = cards + cardUiModel
        uiEvent = MultiCardsUiEvent.AddCard
    }

    fun updateCard(cardUiModel: CardUiModel) {
        cards = cards.map { card -> if (card.id == cardUiModel.id) cardUiModel else card }
        uiEvent = MultiCardsUiEvent.UpdateCard
    }
}
