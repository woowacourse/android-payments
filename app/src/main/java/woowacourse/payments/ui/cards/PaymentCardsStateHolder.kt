package woowacourse.payments.ui.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.domain.PaymentCardStore
import woowacourse.payments.ui.model.mapper.toUiModel

class PaymentCardsStateHolder private constructor(
    private val uiState: MutableState<PaymentCardsUiState>,
) {
    val state: PaymentCardsUiState get() = uiState.value

    fun refreshFromStore() =
        update {
            val cards = PaymentCardStore.getAll().map { it.toUiModel() }
            it.copy(cards = cards)
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
