package woowacourse.payments.ui.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.Expiry
import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.domain.model.Pin
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel

class AddPaymentCardStateHolder private constructor(
    private val uiState: MutableState<AddPaymentCardUiState>,
) {
    val state: AddPaymentCardUiState get() = uiState.value

    fun onCardNumberChange(new: String) = update { it.copy(cardNumber = new) }

    fun onExpiryChange(new: String) = update { it.copy(expiry = new) }

    fun onOwnerChange(new: String) = update { it.copy(owner = new) }

    fun onPinChange(new: String) = update { it.copy(pin = new) }

    fun onBankChange(new: BankType) = update { it.copy(bank = new) }

    fun showSheet() = update { it.copy(isSheetVisible = true) }

    fun hideSheet() = update { it.copy(isSheetVisible = false) }

    val isCardNumberValid: Boolean
        get() = state.cardNumber.isNotEmpty() && CardNumber.from(state.cardNumber) != null
    val isExpiryValid: Boolean
        get() = state.expiry.isNotEmpty() && Expiry.from(state.expiry) != null
    val isPinValid: Boolean
        get() = state.pin.isNotEmpty() && Pin.from(state.pin) != null
    val isBankValid: Boolean get() = (state.bank != null)

    fun buildResult(): PaymentCardUiModel? {
        val bank = state.bank ?: return null
        return PaymentCard
            .create(state.cardNumber, state.expiry, state.owner, state.pin, bank)
            .getOrNull()
            ?.toUiModel()
    }

    private fun update(block: (AddPaymentCardUiState) -> AddPaymentCardUiState) {
        uiState.value = block(uiState.value)
    }

    companion object {
        fun create(initial: AddPaymentCardUiState = AddPaymentCardUiState()): AddPaymentCardStateHolder =
            AddPaymentCardStateHolder(mutableStateOf(initial))

        val Saver: Saver<AddPaymentCardStateHolder, AddPaymentCardUiState> =
            Saver(
                save = { it.state },
                restore = { saved -> create(saved) },
            )
    }
}

@Composable
fun rememberAddPaymentCardStateHolder(initial: AddPaymentCardUiState = AddPaymentCardUiState()): AddPaymentCardStateHolder =
    rememberSaveable(saver = AddPaymentCardStateHolder.Saver) {
        AddPaymentCardStateHolder.create(initial)
    }
