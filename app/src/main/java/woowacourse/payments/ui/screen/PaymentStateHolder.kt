package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

class PaymentStateHolder {
    companion object {
        private const val MIN_CARDS_FOR_TOP_ADD = 2
    }

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> get() = _cards

    val uiCards by derivedStateOf { cards.map { it.toUiModel() } }
    val showTopAdd by derivedStateOf { cards.size >= MIN_CARDS_FOR_TOP_ADD }

    private var nextId = 1L

    private fun assignIdIfNeeded(card: Card): Card = if (card.id == CardUiModel.UNASSIGNED_ID) card.copy(id = nextId++) else card

    private fun add(card: Card) {
        _cards += assignIdIfNeeded(card)
    }

    private fun updateIfChangedById(
        id: Long,
        edited: Card,
    ): Boolean {
        val index = _cards.indexOfFirst { it.id == id }
        if (index < 0) return false
        val fixed = assignIdIfNeeded(edited).copy(id = id)
        if (_cards[index] == fixed) return false
        _cards[index] = fixed
        return true
    }

    fun applyById(
        id: Long?,
        card: Card?,
    ) {
        if (card == null) return
        if (id == null) add(card) else updateIfChangedById(id, card)
    }
}

@Composable
fun rememberPaymentStateHolder(): PaymentStateHolder = remember { PaymentStateHolder() }
