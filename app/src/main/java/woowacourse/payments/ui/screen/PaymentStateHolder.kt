package woowacourse.payments.ui.screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

class PaymentStateHolder {
    private val _uiCards = mutableStateListOf<CardUiModel>()
    val uiCards: List<CardUiModel> get() = _uiCards

    val showTopAdd by derivedStateOf { _uiCards.size >= MIN_CARDS_FOR_TOP_ADD }

    private fun add(card: Card) {
        _uiCards += card.toUiModel()
    }

    private fun updateIfChangedById(
        id: String,
        card: Card,
    ) {
        val index = _uiCards.indexOfFirst { it.id == id }
        if (index < 0) return

        val updated = card.toUiModel().copy(id = id) // 기존 UI id 유지
        if (_uiCards[index] != updated) _uiCards[index] = updated
    }

    fun addOrUpdateById(
        id: String?,
        card: Card?,
    ) {
        if (card == null) return
        if (id.isNullOrEmpty()) add(card) else updateIfChangedById(id, card)
    }

    companion object {
        private const val MIN_CARDS_FOR_TOP_ADD = 2
    }
}
