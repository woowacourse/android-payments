package woowacourse.payments.ui.cardlist.state

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardListStateHolder(
    private val initialCards: List<CardUiModel> = emptyList()
) : Parcelable {
    private val _cards = mutableStateOf(initialCards)

    val cards get() = _cards

    fun addCard(card: CardUiModel) { _cards.value = _cards.value + card }
    fun replaceCard(old: CardUiModel, new: CardUiModel) {
        _cards.value = _cards.value.map { if (it == old) new else it }
    }
}
