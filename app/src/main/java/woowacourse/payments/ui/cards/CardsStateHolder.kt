package woowacourse.payments.ui.cards

import androidx.compose.runtime.toMutableStateList
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsStateHolder(
    cards: List<PaymentCardUiModel> = emptyList(),
) {
    val cardList = cards.toMutableStateList()

    fun addCard(card: PaymentCardUiModel) {
        cardList.add(card)
    }

    fun isAddableWithAddCard(): Boolean = cardList.size <= MINIMUM_CARD_COUNT_FOR_ADD_BUTTON

    fun isAddableWithTopBar(): Boolean = cardList.size > MINIMUM_CARD_COUNT_FOR_ADD_BUTTON

    fun isEmpty(): Boolean = cardList.size == EMPTY_CARDS_SIZE

    companion object {
        private const val MINIMUM_CARD_COUNT_FOR_ADD_BUTTON: Int = 1
        private const val EMPTY_CARDS_SIZE: Int = 0
    }
}
