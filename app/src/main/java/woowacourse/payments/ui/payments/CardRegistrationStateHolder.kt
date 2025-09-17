package woowacourse.payments.ui.payments

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.payments.model.BankUiModel

class CardRegistrationStateHolder(
    initialBankUiModel: BankUiModel,
) {
    val uiState = mutableStateOf(CardInputState(initialBankUiModel))

    fun updateState(newState: CardInputState) {
        uiState.value = newState
    }

    companion object {
        val Saver: Saver<CardRegistrationStateHolder, Any> =
            Saver(
                save = { holder ->
                    holder.uiState.value.bankUiModel
                },
                restore = { restoredBankUiState ->
                    CardRegistrationStateHolder(restoredBankUiState as BankUiModel)
                },
            )
    }
}
