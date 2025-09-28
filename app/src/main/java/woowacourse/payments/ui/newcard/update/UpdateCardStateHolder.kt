package woowacourse.payments.ui.newcard.update

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import woowacourse.payments.data.CardStore
import woowacourse.payments.data.CardStore.cards
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.toLocalBankUiModel
import woowacourse.payments.ui.newcard.create.CreateCardUiEvent
import woowacourse.payments.ui.newcard.state.holder.NewCardContentStateHolder

class UpdateCardStateHolderSaver : Saver<UpdateCardStateHolder, UpdateCardUiState> {
    override fun SaverScope.save(value: UpdateCardStateHolder): UpdateCardUiState? =
        value.uiState.copy(newCardContentUiState = value.uiState.newCardContentUiState)


    override fun restore(value: UpdateCardUiState): UpdateCardStateHolder? {
        return UpdateCardStateHolder(NewCardContentStateHolder(value.newCardContentUiState))
    }
}

class UpdateCardStateHolder(
    private val newCardContentStateHolder: NewCardContentStateHolder = NewCardContentStateHolder(),
) {

    fun selectableCardBanks() = BankType.entries.map { it.toLocalBankUiModel() }

    var uiState by mutableStateOf(UpdateCardUiState())
        private set

    var uiEvent: UpdateCardUiEvent? by mutableStateOf(null)
        private set

    fun updateCard(cardId: Long) {
        val newCard = newCardContentStateHolder.newCard(cardId)
        uiEvent = UpdateCardUiEvent.UpdateCard
        CardStore.updateCard(newCard)
    }

    fun updateCardInfo(cardId: Long) {
        val card = cards.find { it.id == cardId } ?: return
        updateCardBank(card.bankType.toLocalBankUiModel())
        updateCardNumber(card.cardNumbers)
        updateExpiryDate(card.cardExpiry)
        updateOwnerName(card.ownerName)
        updatePassword(card.password)
    }

    fun updateCardBank(bankUiModel: woowacourse.payments.ui.model.BankUiModel) {
        newCardContentStateHolder.updateCardBank(bankUiModel)
        uiState = uiState.copy(
            newCardContentUiState = newCardContentStateHolder.cardCreateState
        )
    }

    fun updateCardNumber(cardNumber: String) {
        newCardContentStateHolder.updateCardNumber(cardNumber)
        uiState = uiState.copy(
            newCardContentUiState = newCardContentStateHolder.cardCreateState
        )
    }

    fun updateExpiryDate(expiryDate: String) {
        newCardContentStateHolder.updateExpiryDate(expiryDate)
        uiState = uiState.copy(
            newCardContentUiState = newCardContentStateHolder.cardCreateState
        )
    }

    fun updateOwnerName(ownerName: String) {
        newCardContentStateHolder.updateOwnerName(ownerName)
        uiState = uiState.copy(
            newCardContentUiState = newCardContentStateHolder.cardCreateState
        )
    }

    fun updatePassword(password: String) {
        newCardContentStateHolder.updatePassword(password)
        uiState = uiState.copy(
            newCardContentUiState = newCardContentStateHolder.cardCreateState
        )
    }

    fun isCardUpdatable(cardId: Long) =
        uiState.newCardContentUiState.bankUiModel != null &&
                uiState.newCardContentUiState.run {
                    isCardNumberCreatable(cardNumber) &&
                            isExpiryDateCreatable(
                                expiryDate,
                                expiryDateErrorTextRes,
                            ) &&
                            isOwnerNameCreatable(ownerName) &&
                            isPasswordCreatable(password) &&
                            !isSamePreviousCard(cardId)
                }

    private fun isSamePreviousCard(cardId: Long): Boolean {
        val card = cards.find { it.id == cardId } ?: return false
        return uiState.newCardContentUiState.run {
            card.bankType.toLocalBankUiModel() == bankUiModel &&
                    card.cardNumbers == cardNumber &&
                    card.cardExpiry == expiryDate &&
                    card.ownerName == ownerName &&
                    card.password == password
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