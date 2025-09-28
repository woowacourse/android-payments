package woowacourse.payments.ui.cards.single

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import woowacourse.payments.data.CardStore
import woowacourse.payments.ui.core.mapper.toCardUiModel
import woowacourse.payments.ui.model.CardUiModel

class SingleCardStateHolderSaver : Saver<SingleCardStateHolder, CardUiModel> {
    override fun SaverScope.save(value: SingleCardStateHolder): CardUiModel? = value.card

    override fun restore(value: CardUiModel): SingleCardStateHolder? = SingleCardStateHolder(
        value.id
    )
}

class SingleCardStateHolder(
    initCardId: Long
) {
    var card: CardUiModel by mutableStateOf(CardStore.fetch(initCardId))
        private set

    var uiEvent: SingleCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard() {
        uiEvent = SingleCardsUiEvent.AddCard(CardStore.fetchAll().map { it.id })
    }

    fun updateCard() {
        card = CardStore.cards.first().toCardUiModel()
        uiEvent = SingleCardsUiEvent.UpdateCard
    }
}
