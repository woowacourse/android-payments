package woowacourse.payments.ui.cards

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import woowacourse.payments.domain.Card

class CardsStateHolder {
    val cardsState = mutableStateListOf<Card>()

    val isNewCardVisible by derivedStateOf { cardsState.size <= 1 }

    val isCardRegisterMessageVisible by derivedStateOf { cardsState.size == 0 }

    val isRegisteredCardsVisible by derivedStateOf { cardsState.size >= 1 }

    val isRegisterCardButtonVisible by derivedStateOf { cardsState.size > 1 }

    fun updateCardsView(newCard: Card) {
        val cardIndex = cardsState.indexOfFirst { it.id == newCard.id }

        if (cardIndex == INVALID_INDEX) {
            cardsState.add(newCard)
        } else {
            cardsState[cardIndex] = newCard
        }
    }

    companion object {
        private const val INVALID_INDEX: Int = -1

        val Saver: Saver<CardsStateHolder, Any> =
            listSaver(
                save = { stateHolder: CardsStateHolder -> stateHolder.cardsState.toList() },
                restore = { list: List<Card> -> CardsStateHolder().apply { cardsState.addAll(list) } },
            )
    }
}
