package woowacourse.payments.ui.cardlist.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

data class CardListStateHolder(
    val cardListUiState: MutableState<CardListUiStatus> = mutableStateOf(CardListUiStatus.EmptyCardList)
) {
    var uiState by cardListUiState
        private set

    fun addCard(card: CardUiModel) {
        when (val s = uiState) {
            is CardListUiStatus.EmptyCardList -> {
                uiState = CardListUiStatus.OneCardList(card)
            }

            is CardListUiStatus.OneCardList -> {
                uiState = CardListUiStatus.MultiCardList(listOf(s.card, card))
            }

            is CardListUiStatus.MultiCardList -> {
                uiState = CardListUiStatus.MultiCardList(s.card + card)
            }
        }
    }

    fun replaceCard(old: CardUiModel, new: CardUiModel) {
        when (val s = uiState) {
            is CardListUiStatus.EmptyCardList -> null
            is CardListUiStatus.OneCardList -> {
                uiState = CardListUiStatus.OneCardList(new)
            }

            is CardListUiStatus.MultiCardList -> {
                uiState =
                    CardListUiStatus.MultiCardList(s.card.map { card -> if (card == old) new else card })
            }
        }
    }
}
