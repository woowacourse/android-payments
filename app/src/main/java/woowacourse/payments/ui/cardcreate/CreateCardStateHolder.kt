package woowacourse.payments.ui.cardcreate

import androidx.annotation.StringRes
import androidx.compose.runtime.derivedStateOf
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

class CreateCardStateHolder(
    initial: CreateCardUiState = CreateCardUiState()
) {
    var cardCreateState by mutableStateOf(initial)
        private set


    val isCardCreatable: Boolean by derivedStateOf {
        isCardNumberCreatable(cardCreateState.cardNumber) &&
                isExpiryDateCreatable(
                    cardCreateState.expiryDate,
                    cardCreateState.expiryDateErrorTextRes
                ) &&
                cardCreateState.expiryDateErrorTextRes == null &&
                isOwnerNameCreatable(cardCreateState.ownerName) &&
                isPasswordCreatable(cardCreateState.password)
    }

    fun newCard(): PaymentCardUiModel =
        cardCreateState.run { PaymentCardUiModel(cardNumber, expiryDate, ownerName) }

    fun updateCardNumber(cardNumber: String) {
        val value = cardNumber.trim()
        if (!isCardNumberAcceptable(value)) return
        cardCreateState = cardCreateState.copy(cardNumber = value)
    }

    fun updateExpiryDate(expiryDate: String) {
        val value = expiryDate.trim()
        if (!isExpiryDateAcceptable(value)) return
        cardCreateState = cardCreateState.copy(expiryDate = value)
        val error = cardCreateState.expiryDate.formatCardExpiryException()
        cardCreateState = cardCreateState.copy(expiryDateErrorTextRes = error?.toErrorResourceId())
    }

    fun updateOwnerName(ownerName: String) {
        val value = ownerName.trim()
        if (!isOwnerNameAcceptable(value)) return
        cardCreateState = cardCreateState.copy(ownerName = value)
    }

    fun updatePassword(password: String) {
        val value = password.trim()
        if (!isPasswordAcceptable(value)) return
        cardCreateState = cardCreateState.copy(password = value)
    }

    private fun isCardNumberAcceptable(number: String): Boolean =
        number.length <= CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateAcceptable(expiry: String): Boolean =
        expiry.length <= CARD_EXPIRY_DATE_MAX && expiry.isDigitsOnly()

    private fun isOwnerNameAcceptable(name: String): Boolean =
        name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordAcceptable(password: String): Boolean =
        password.length <= CARD_PASSWORD_MAX && password.isDigitsOnly()

    private fun isCardNumberCreatable(number: String): Boolean =
        number.length == CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateCreatable(expiry: String, @StringRes errorRes: Int?): Boolean =
        expiry.length == CARD_EXPIRY_DATE_MAX &&
                expiry.isDigitsOnly() &&
                errorRes == null

    private fun isOwnerNameCreatable(name: String): Boolean =
        name.isNotBlank() && name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordCreatable(password: String): Boolean =
        password.length == CARD_PASSWORD_MAX && password.isDigitsOnly()

    companion object {
        private const val CARD_NUMBERS_MAX = 16
        private const val CARD_EXPIRY_DATE_MAX = 4
        const val CARD_OWNER_NAME_MAX = 30
        private const val CARD_PASSWORD_MAX = 4
    }
}
