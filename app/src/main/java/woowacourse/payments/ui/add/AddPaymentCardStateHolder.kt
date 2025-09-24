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

class AddPaymentCardStateHolder private constructor(
    private val uiState: MutableState<AddPaymentCardUiState>,
) {
    val state: AddPaymentCardUiState get() = uiState.value

    private var cardId: String? = null
    private var isEdited: Boolean = false

    fun onCardNumberChange(new: String) =
        update {
            isEdited = true
            it.copy(cardNumber = new)
        }

    fun onExpiryChange(new: String) =
        update {
            isEdited = true
            it.copy(expiry = new)
        }

    fun onOwnerChange(new: String) =
        update {
            isEdited = true
            it.copy(owner = new)
        }

    fun onPinChange(new: String) =
        update {
            isEdited = true
            it.copy(pin = new)
        }

    fun onBankChange(new: BankType) =
        update {
            isEdited = true
            it.copy(bank = new)
        }

    fun showSheet() = update { it.copy(isSheetVisible = true) }

    fun hideSheet() = update { it.copy(isSheetVisible = false) }

    val isCardNumberValid: Boolean
        get() = state.cardNumber.isNotEmpty() && CardNumber.from(state.cardNumber) != null
    val isExpiryValid: Boolean
        get() = state.expiry.isNotEmpty() && Expiry.from(state.expiry) != null
    val isPinValid: Boolean
        get() = state.pin.isNotEmpty() && Pin.from(state.pin) != null
    val isBankValid: Boolean get() = (state.bank != null)
    val canSave: Boolean
        get() =
            isEdited &&
                isCardNumberValid &&
                isExpiryValid &&
                isPinValid

    fun beginEdit(card: PaymentCard) {
        cardId = card.id
        update {
            it.copy(
                cardNumber = card.cardNumber.value,
                expiry = card.expiry.value,
                owner = card.owner,
                pin = card.pin.value,
                bank = card.bank,
                isEditing = true,
            )
        }
        isEdited = false
    }

    fun buildResult(): PaymentCard? {
        val bank = state.bank ?: return null
        val newCard =
            PaymentCard
                .create(state.cardNumber, state.expiry, state.owner, state.pin, bank)
                .getOrNull() ?: return null

        return cardId?.let { newCard.copy(id = it) } ?: newCard
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
