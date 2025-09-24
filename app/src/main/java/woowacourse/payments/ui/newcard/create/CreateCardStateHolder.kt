package woowacourse.payments.ui.newcard.create

import androidx.annotation.StringRes
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import woowacourse.payments.ui.newcard.state.holder.NewCardContentStateAction
import woowacourse.payments.ui.newcard.state.holder.NewCardContentStateHolder

class CreateCardStateHolderSaver : Saver<CreateCardStateHolder, CreateCardUiState> {
    override fun SaverScope.save(value: CreateCardStateHolder): CreateCardUiState? {
        return value.uiState.copy(newCardContentUiState = value.uiState.newCardContentUiState)
    }

    override fun restore(value: CreateCardUiState): CreateCardStateHolder? =
        CreateCardStateHolder(NewCardContentStateHolder(value.newCardContentUiState))
}

class CreateCardStateHolder(
    private val newCardContentStateHolder: NewCardContentStateHolder = NewCardContentStateHolder()
) : NewCardContentStateAction by newCardContentStateHolder {
    var uiState by mutableStateOf(CreateCardUiState(newCardContentStateHolder.cardCreateState))
        private set

    val isCardCreatable by derivedStateOf {
        uiState.newCardContentUiState.bankUiModel != null &&
                uiState.newCardContentUiState.run {
                    isCardNumberCreatable(cardNumber) &&
                            isExpiryDateCreatable(
                                expiryDate,
                                expiryDateErrorTextRes,
                            ) &&
                            isOwnerNameCreatable(ownerName) &&
                            isPasswordCreatable(password)
                }
    }

    private fun isCardNumberCreatable(number: String): Boolean =
        number.length == CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateCreatable(
        expiry: String,
        @StringRes errorRes: Int?,
    ): Boolean =
        expiry.length == CARD_EXPIRY_DATE_MAX &&
                expiry.isDigitsOnly() &&
                errorRes == null

    private fun isOwnerNameCreatable(name: String): Boolean = name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordCreatable(password: String): Boolean =
        password.length == CARD_PASSWORD_MAX && password.isDigitsOnly()

    companion object {
        private const val CARD_NUMBERS_MAX = 16
        private const val CARD_EXPIRY_DATE_MAX = 4
        private const val CARD_OWNER_NAME_MAX = 30
        private const val CARD_PASSWORD_MAX = 4
    }
}