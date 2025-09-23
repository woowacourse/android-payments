package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel

class PaymentStateHolder {
    companion object {
        const val MAX_CARDS = 2
    }

    var cards by mutableStateOf(emptyList<CardUiModel>())
        private set

    val canAddMore by derivedStateOf { cards.size < MAX_CARDS }
    val showTopAdd by derivedStateOf { cards.size >= MAX_CARDS }

    fun onCardAdded(card: CardUiModel) {
        if (!canAddMore) return
        cards = cards + card
    }
}

@Composable
fun rememberPaymentStateHolder(): PaymentStateHolder = remember { PaymentStateHolder() }
