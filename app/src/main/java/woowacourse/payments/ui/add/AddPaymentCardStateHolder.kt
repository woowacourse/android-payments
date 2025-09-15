package woowacourse.payments.ui.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.Expiry
import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.domain.model.Pin
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel

class AddPaymentCardStateHolder private constructor(
    private val stateHolder: MutableState<AddPaymentCardUiState>,
) {
    val state: AddPaymentCardUiState get() = stateHolder.value

    fun onCardNumberChange(new: String) = update { it.copy(cardNumber = new) }

    fun onExpiryChange(new: String) = update { it.copy(expiry = new) }

    fun onOwnerChange(new: String) = update { it.copy(owner = new) }

    fun onPinChange(new: String) = update { it.copy(pin = new) }

    val isCardNumberValid: Boolean
        get() = state.cardNumber.isNotEmpty() && CardNumber.from(state.cardNumber) != null
    val isExpiryValid: Boolean
        get() = state.expiry.isNotEmpty() && Expiry.from(state.expiry) != null
    val isPinValid: Boolean
        get() = state.pin.isNotEmpty() && Pin.from(state.pin) != null

    fun buildResult(): Result<PaymentCardUiModel> =
        PaymentCard
            .create(state.cardNumber, state.expiry, state.owner, state.pin)
            .map { it.toUiModel() }

    private inline fun update(block: (AddPaymentCardUiState) -> AddPaymentCardUiState) {
        stateHolder.value = block(stateHolder.value)
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
