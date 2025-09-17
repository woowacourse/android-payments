package woowacourse.payments.ui.payments

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.payments.model.BankUiModel

class CardRegistrationStateHolder(
    initState: BankUiModel,
) {
    val uiState = mutableStateOf(initState)

    fun updateState(newState: BankUiModel) {
        uiState.value = newState
    }

    companion object {
        val Saver: Saver<CardRegistrationStateHolder, Any> =
            Saver(
                save = { holder ->
                    holder.uiState.value
                },
                restore = { restoredBankUiState ->
                    CardRegistrationStateHolder(restoredBankUiState as BankUiModel)
                },
            )
    }
}
