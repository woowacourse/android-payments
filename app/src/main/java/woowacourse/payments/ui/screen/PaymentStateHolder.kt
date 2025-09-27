package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.toUiModel

class PaymentStateHolder {
    companion object {
        private const val MIN_CARDS_FOR_TOP_ADD = 2
    }

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> get() = _cards

    val uiCards by derivedStateOf { cards.map { it.toUiModel() } }
    val showTopAdd by derivedStateOf { cards.size >= MIN_CARDS_FOR_TOP_ADD }

    private fun add(card: Card) {
        _cards += card
    }

    private fun updateIfChanged(
        index: Int,
        edited: Card,
    ): Boolean {
        if (index !in _cards.indices) return false
        val before = _cards[index]
        if (before == edited) return false
        _cards[index] = edited
        return true
    }

    fun apply(
        index: Int?,
        card: Card?,
    ) {
        if (card == null) return
        if (index == null) add(card) else updateIfChanged(index, card)
    }
}

@Composable
fun rememberPaymentStateHolder(): PaymentStateHolder = remember { PaymentStateHolder() }
