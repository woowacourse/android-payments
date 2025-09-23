package woowacourse.payments.ui.cards

import androidx.compose.runtime.toMutableStateList
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsStateHolder(
    cards: List<PaymentCardUiModel> = emptyList(),
) {
    private val _cardList = cards.toMutableStateList()
    val cardList get() = _cardList.toList()

    fun addCard(card: PaymentCardUiModel) {
        _cardList.add(card)
    }

    fun isAddableWithAddCard(): Boolean = cardList.size <= MINIMUM_CARD_COUNT_FOR_ADD_BUTTON

    fun isAddableWithTopBar(): Boolean = cardList.size > MINIMUM_CARD_COUNT_FOR_ADD_BUTTON

    fun isEmpty(): Boolean = cardList.size == EMPTY_CARDS_SIZE

    fun upsertCard(card: PaymentCardUiModel) {
        val index = _cardList.indexOfFirst { it.id == card.id }
        if (index >= 0) {
            _cardList[index] = card
        } else {
            val newCard = card.copy(id = _cardList.size + 1)
            _cardList.add(newCard)
        }
    }

    companion object {
        private const val MINIMUM_CARD_COUNT_FOR_ADD_BUTTON: Int = 1
        private const val EMPTY_CARDS_SIZE: Int = 0
    }
}
