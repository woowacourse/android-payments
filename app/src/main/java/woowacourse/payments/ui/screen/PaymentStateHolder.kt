package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.toUiModel

class PaymentStateHolder {
    companion object {
        const val MAX_CARDS = 2
    }

    var cards by mutableStateOf(emptyList<Card>())
        private set

    val uiCards by derivedStateOf { cards.map { it.toUiModel() } }
    val canAddMore by derivedStateOf { cards.size < MAX_CARDS }
    val showTopAdd by derivedStateOf { !canAddMore }

    var editingIndex: Int? by mutableStateOf(null)
        private set

    fun onCardAdded(card: Card) {
        if (canAddMore) {
            cards = cards + card
        }
    }

    fun beginEditAt(index: Int): Boolean =
        (index in cards.indices).also { valid ->
            if (valid) editingIndex = index
        }

    fun commitEdit(edited: Card) {
        val index =
            editingIndex ?: run {
                editingIndex = null
                return
            }
        if (index in cards.indices) {
            cards = cards.toMutableList().apply { this[index] = edited }
        }
        editingIndex = null
    }

    fun applyResult(card: Card) {
        if (editingIndex != null) {
            commitEdit(card)
        } else {
            onCardAdded(card)
        }
    }

    fun cancelEdit() {
        editingIndex = null
    }
}

@Composable
fun rememberPaymentStateHolder(): PaymentStateHolder = remember { PaymentStateHolder() }
