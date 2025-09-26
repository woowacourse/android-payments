package woowacourse.payments.ui.cards

import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
class CardsStateHolder : Parcelable {
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
    }
}
