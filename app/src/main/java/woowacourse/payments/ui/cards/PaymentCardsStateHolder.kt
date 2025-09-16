package woowacourse.payments.ui.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.model.PaymentCardUiModel

class PaymentCardsStateHolder private constructor(
    private val uiState: MutableState<PaymentCardsUiState>,
) {
    val state: PaymentCardsUiState get() = uiState.value

    fun addCard(card: PaymentCardUiModel) =
        update {
            it.copy(cards = it.cards + card)
        }

    private inline fun update(block: (PaymentCardsUiState) -> PaymentCardsUiState) {
        uiState.value = block(uiState.value)
    }

    companion object {
        fun create(initial: PaymentCardsUiState = PaymentCardsUiState()): PaymentCardsStateHolder =
            PaymentCardsStateHolder(mutableStateOf(initial))

        val Saver: Saver<PaymentCardsStateHolder, PaymentCardsUiState> =
            Saver(
                save = { it.state },
                restore = { saved -> create(saved) },
            )
    }
}

@Composable
fun rememberPaymentCardsStateHolder(initial: PaymentCardsUiState = PaymentCardsUiState()): PaymentCardsStateHolder =
    rememberSaveable(saver = PaymentCardsStateHolder.Saver) {
        PaymentCardsStateHolder.create(initial)
    }
