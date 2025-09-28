package woowacourse.payments.ui.cards.multi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.data.CardStore
import woowacourse.payments.ui.core.mapper.toCardUiModel
import woowacourse.payments.ui.model.CardUiModel

class MultiCardsStateHolderSaver : Saver<MultiCardsStateHolder, List<CardUiModel>> {
    override fun SaverScope.save(value: MultiCardsStateHolder): List<CardUiModel>? = value.cards

    override fun restore(value: List<CardUiModel>): MultiCardsStateHolder? =
        MultiCardsStateHolder(value.map { it.id })
}

class MultiCardsStateHolder(
    initCardIds: List<Long>
) {
    var cards: List<CardUiModel> by mutableStateOf(initCardIds.map { CardStore.fetch(it) })
        private set

    var uiEvent: MultiCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard(cardId: Long) {
        val addedCard = CardStore.cards.find { it.id == cardId }?.toCardUiModel() ?: return
        cards = cards + addedCard
        uiEvent = MultiCardsUiEvent.AddCard
    }

    fun updateCard() {
        cards = CardStore.cards.map { it.toCardUiModel() }
        uiEvent = MultiCardsUiEvent.UpdateCard
    }
}
