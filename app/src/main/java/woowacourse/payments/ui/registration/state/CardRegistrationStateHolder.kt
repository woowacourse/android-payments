package woowacourse.payments.ui.registration.state

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

class CardRegistrationStateHolder(
    uiState: CardRegistrationScreenUiState = CardRegistrationScreenUiState(),
) {
    var uiState by mutableStateOf(uiState)
    var cardExpirationErrorMessageResource: Int? by mutableStateOf(null)

    fun updateBottomSheetVisible(isOpen: Boolean) {
        uiState = uiState.copy(isBottomSheetOpen = isOpen)
    }

    fun updateCardNumber(number: String) {
        val newCardNumber = runCatching { CardNumberUiModel(number) }.getOrNull() ?: return
        val updatedCard = uiState.card.copy(cardNumberUiModel = newCardNumber)
        uiState = uiState.copy(card = updatedCard)
    }

    fun updateCardExpirationDate(expirationDate: String) {
        val newCardExpirationDate =
            runCatching { CardExpirationDateUiModel(expirationDate) }.getOrNull() ?: return
        val updatedCard = uiState.card.copy(cardExpirationDateUiModel = newCardExpirationDate)
        uiState = uiState.copy(card = updatedCard)
    }

    fun updateCardholderName(name: String) {
        val newCardholderName =
            runCatching { CardholderNameUiModel(name.uppercase()) }.getOrNull() ?: return
        val updatedCard = uiState.card.copy(cardholderNameUiModel = newCardholderName)
        uiState = uiState.copy(card = updatedCard)
    }

    fun updatePassword(password: String) {
        val newPassword = runCatching { CardPasswordUiModel(password) }.getOrNull() ?: return
        val updatedCard = uiState.card.copy(cardPasswordUiModel = newPassword)
        uiState = uiState.copy(card = updatedCard)
    }

    fun updateCardCompany(cardCompany: CardCompanyUiModel) {
        if (cardCompany == CardCompanyUiModel.NOT_SELECT) return
        val updatedCard = uiState.card.copy(cardCompanyUiModel = cardCompany)
        uiState = uiState.copy(card = updatedCard)
    }

    val isRegistrableCard: Boolean
        get() =
            uiState.card.run {
                cardNumberUiModel.isValid() && cardExpirationDateUiModel.isValid() && cardExpirationErrorMessageResource == null &&
                    cardPasswordUiModel.isValid() &&
                    cardCompanyUiModel.isSelect()
            }

    fun updateExpirationDateErrorMessage(
        @StringRes resource: Int?,
    ) {
        cardExpirationErrorMessageResource = resource
    }

    companion object {
        private const val SAVER_UI_STATE_KEY = "uiState"

        val Saver: Saver<CardRegistrationStateHolder, *> =
            mapSaver(
                save = { holder -> mapOf(SAVER_UI_STATE_KEY to holder.uiState) },
                restore = { map ->
                    val uiState = map[SAVER_UI_STATE_KEY] as CardRegistrationScreenUiState
                    CardRegistrationStateHolder(uiState = uiState)
                },
            )
    }
}
