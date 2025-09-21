package woowacourse.payments.ui.cards

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.Card

class CardsStateHolder {
    val cardsState = mutableStateListOf<Card>()

    val isNewCardVisible by derivedStateOf { cardsState.size <= 1 }

    val isCardRegisterMessageVisible by derivedStateOf { cardsState.size == 0 }

    val isRegisteredCardsVisible by derivedStateOf { cardsState.size >= 1 }

    val isRegisterCardButtonVisible by derivedStateOf { cardsState.size > 1 }
}
