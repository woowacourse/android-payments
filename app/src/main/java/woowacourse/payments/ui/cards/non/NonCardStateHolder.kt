package woowacourse.payments.ui.cards.non

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class NonCardStateHolder {

    var uiEvent: NonCardsUiEvent? by mutableStateOf(null)
        private set

    fun addCard(cardUiModel: CardUiModel) {
        uiEvent = NonCardsUiEvent.AddCard(cardUiModel)
    }
}
