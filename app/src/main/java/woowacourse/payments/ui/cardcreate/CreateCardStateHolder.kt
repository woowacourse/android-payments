package woowacourse.payments.ui.cardcreate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.core.text.isDigitsOnly
import woowacourse.payments.ui.cardcreate.model.CreateCardUiState
import woowacourse.payments.ui.utils.ext.formatCardExpiryException
import woowacourse.payments.ui.utils.ext.toErrorResourceId

class CreateCardStateHolderSaver : Saver<CreateCardStateHolder, CreateCardUiState> {
    override fun SaverScope.save(value: CreateCardStateHolder): CreateCardUiState? =
        value.cardCreateState.value

    override fun restore(value: CreateCardUiState): CreateCardStateHolder? =
        CreateCardStateHolder(value)
}

class CreateCardStateHolder(initialCardCreateState: CreateCardUiState = CreateCardUiState()) {
    private var _cardCreateState = mutableStateOf(initialCardCreateState)
    val cardCreateState: State<CreateCardUiState> get() = _cardCreateState

    fun updateCreateCardNumbers(cardNumbers: String) {
        if (!cardNumbers.isDigitsOnly() || cardNumbers.length > CARD_NUMBERS_MAX) return
        _cardCreateState.value = cardCreateState.value.copy(cardNumber = cardNumbers)
    }

    fun updateCreateCardExpiryDate(expiryDate: String) {
        if (!expiryDate.isDigitsOnly() || expiryDate.length > CARD_EXPIRY_DATE_MAX) return
        _cardCreateState.value = cardCreateState.value.copy(expiryDate = expiryDate)
        val expiryDateException = cardCreateState.value.expiryDate.formatCardExpiryException()
        _cardCreateState.value =
            _cardCreateState.value.copy(expiryDateErrorTextRes = expiryDateException?.toErrorResourceId())
    }

    fun updateCreateCardOwnerName(ownerName: String) {
        if (ownerName.length > CARD_OWNER_NAME_MAX) return
        _cardCreateState.value = cardCreateState.value.copy(ownerName = ownerName)
    }

    fun updateCreateCardPassword(password: String) {
        if (!password.isDigitsOnly() || password.length > CARD_PASSWORD_MAX) return
        _cardCreateState.value = cardCreateState.value.copy(password = password)
    }

    companion object {
        private const val CARD_NUMBERS_MAX = 16
        private const val CARD_EXPIRY_DATE_MAX = 4
        const val CARD_OWNER_NAME_MAX = 30
        private const val CARD_PASSWORD_MAX = 4
    }
}