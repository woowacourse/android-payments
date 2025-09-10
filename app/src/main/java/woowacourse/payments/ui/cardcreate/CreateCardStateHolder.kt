package woowacourse.payments.ui.cardcreate

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import woowacourse.payments.ui.cardcreate.model.CreateCardUiState
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.utils.ext.formatCardExpiryException
import woowacourse.payments.ui.utils.ext.toErrorResourceId

class CreateCardStateHolderSaver : Saver<CreateCardStateHolder, CreateCardUiState> {
    override fun SaverScope.save(value: CreateCardStateHolder): CreateCardUiState? =
        value.cardCreateState

    override fun restore(value: CreateCardUiState): CreateCardStateHolder? =
        CreateCardStateHolder(value)
}

class CreateCardStateHolder(initialCardCreateState: CreateCardUiState = CreateCardUiState()) {
    var cardCreateState by mutableStateOf(initialCardCreateState)
        private set

    fun newCard(): PaymentCardUiModel =
        cardCreateState.run {
            PaymentCardUiModel(cardNumber, expiryDate, ownerName)
        }


    fun updateCreateCardNumbers(cardNumbers: String) {
        if (!cardNumbers.isDigitsOnly() || cardNumbers.length > CARD_NUMBERS_MAX) return
        cardCreateState = cardCreateState.copy(cardNumber = cardNumbers)
    }

    fun updateCreateCardExpiryDate(expiryDate: String) {
        if (!expiryDate.isDigitsOnly() || expiryDate.length > CARD_EXPIRY_DATE_MAX) return
        cardCreateState = cardCreateState.copy(expiryDate = expiryDate)
        val expiryDateException = cardCreateState.expiryDate.formatCardExpiryException()
        cardCreateState =
            cardCreateState.copy(expiryDateErrorTextRes = expiryDateException?.toErrorResourceId())
    }

    fun updateCreateCardOwnerName(ownerName: String) {
        if (ownerName.length > CARD_OWNER_NAME_MAX) return
        cardCreateState = cardCreateState.copy(ownerName = ownerName)
    }

    fun updateCreateCardPassword(password: String) {
        if (!password.isDigitsOnly() || password.length > CARD_PASSWORD_MAX) return
        cardCreateState = cardCreateState.copy(password = password)
    }

    companion object {
        private const val CARD_NUMBERS_MAX = 16
        private const val CARD_EXPIRY_DATE_MAX = 4
        const val CARD_OWNER_NAME_MAX = 30
        private const val CARD_PASSWORD_MAX = 4
    }
}