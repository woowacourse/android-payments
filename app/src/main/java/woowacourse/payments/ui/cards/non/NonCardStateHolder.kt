package woowacourse.payments.ui.cards.non

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.data.CardStore

class NonCardStateHolder {

    var uiEvent: NonCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard(cardId: Long) {
        val addedCard = CardStore.cards.find { it.id == cardId } ?: return
        uiEvent = NonCardsUiEvent.AddedCard(addedCard.id)
    }
}
