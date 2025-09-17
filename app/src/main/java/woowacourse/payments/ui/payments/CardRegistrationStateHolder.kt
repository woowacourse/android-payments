package woowacourse.payments.ui.payments

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.payments.model.BankUiState

class CardRegistrationStateHolder(
    initialBankUiState: BankUiState,
) {
    val uiState = mutableStateOf(CardInputState(initialBankUiState))

    fun updateState(newState: CardInputState) {
        uiState.value = newState
    }

    companion object {
        val Saver: Saver<CardRegistrationStateHolder, Any> =
            Saver(
                save = { holder ->
                    holder.uiState.value.bankUiState
                },
                restore = { restoredBankUiState ->
                    CardRegistrationStateHolder(restoredBankUiState as BankUiState)
                },
            )
    }
}
