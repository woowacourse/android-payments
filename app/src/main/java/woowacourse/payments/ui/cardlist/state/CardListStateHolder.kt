package woowacourse.payments.ui.cardlist.state

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardListStateHolder(
    val cardListUiState: CardListUiStatus = CardListUiStatus.EmptyCardList
) : Parcelable {
    private val _uiState: MutableState<CardListUiStatus> =  mutableStateOf(cardListUiState)
    val uiState get() = _uiState

    fun addCard(card: CardUiModel) {
        when (val s = _uiState.value) {
            is CardListUiStatus.EmptyCardList -> {
                _uiState.value = CardListUiStatus.OneCardList(card)
            }

            is CardListUiStatus.OneCardList -> {
                _uiState.value = CardListUiStatus.MultiCardList(listOf(s.card, card))
            }

            is CardListUiStatus.MultiCardList -> {
                _uiState.value = CardListUiStatus.MultiCardList(s.card + card)
            }
        }
    }

    fun replaceCard(old: CardUiModel, new: CardUiModel) {
        when (val s = _uiState.value) {
            is CardListUiStatus.EmptyCardList -> null
            is CardListUiStatus.OneCardList -> {
                _uiState.value = CardListUiStatus.OneCardList(new)
            }

            is CardListUiStatus.MultiCardList -> {
                _uiState.value =
                    CardListUiStatus.MultiCardList(s.card.map { card -> if (card == old) new else card })
            }
        }
    }
}
