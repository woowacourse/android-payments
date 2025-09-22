package woowacourse.payments.ui.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.payments.model.BankUiModel

class CardRegistrationStateHolder(
    initState: BankUiModel,
) {
    var uiState: BankUiModel by mutableStateOf(initState)
        private set

    fun updateState(newState: BankUiModel) {
        uiState = newState
    }

    companion object {
        val Saver: Saver<CardRegistrationStateHolder, Any> =
            Saver(
                save = { holder ->
                    holder.uiState
                },
                restore = { restoredBankUiState ->
                    CardRegistrationStateHolder(restoredBankUiState as BankUiModel)
                },
            )
    }
}
