package woowacourse.payments.ui.screen.addCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password

class AddCardStateHolder(
    initialState: AddCardUiState = AddCardUiState(),
    private val originalState: AddCardUiState = initialState,
) {
    val allBanks: List<BankType> = BankType.entries

    var uiState by mutableStateOf(initialState)
        private set

    val isEditMode: Boolean = originalState != AddCardUiState()

    fun updateCardNumber(newNumber: String) {
        uiState = uiState.copy(cardNumber = newNumber)
    }

    fun updateExpired(newExpired: String) {
        uiState = uiState.copy(expired = newExpired)
    }

    fun updateCardOwner(newOwner: String) {
        uiState = uiState.copy(cardOwner = newOwner)
    }

    fun updatePassword(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun updateBank(newBank: BankType) {
        uiState =
            uiState.copy(cardCompanySelectionState = CardCompanySelectionState.Selected(newBank))
    }

    fun validate(): Boolean {
        val errors = mutableSetOf<AddCardError>()
        val cardNumberValue = uiState.cardNumber
        val expiredValue = uiState.expired
        val ownerValue = uiState.cardOwner
        val passwordValue = uiState.password

        if (!CardNumber(cardNumberValue).isValid) errors.add(AddCardError.CARD_NUMBER_INVALID)
        if (!Expired(expiredValue).isValid) errors.add(AddCardError.EXPIRED_INVALID)
        if (!CardOwner(ownerValue).isValid) errors.add(AddCardError.OWNER_INVALID)
        if (!Password(passwordValue).isValid) errors.add(AddCardError.PASSWORD_INVALID)

        uiState = uiState.copy(errors = errors, submitted = true)
        return errors.isEmpty()
    }

    fun hasChanges(): Boolean =
        uiState.cardNumber != originalState.cardNumber ||
            uiState.expired != originalState.expired ||
            uiState.cardOwner != originalState.cardOwner ||
            uiState.cardCompanySelectionState != originalState.cardCompanySelectionState

    companion object {
        val saver: Saver<AddCardStateHolder, AddCardUiState> =
            Saver(
                save = { holder -> holder.uiState },
                restore = { state -> AddCardStateHolder(state) },
            )
    }
}
