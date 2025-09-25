package woowacourse.payments.ui.registration.state

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
    isBottomSheetOpen: Boolean = true,
) {
    var uiState by mutableStateOf(uiState)
    var isBottomSheetOpen by mutableStateOf(isBottomSheetOpen)

    fun updateBottomSheetVisible(isOpen: Boolean) {
        isBottomSheetOpen = isOpen
    }

    fun updateCardNumber(number: String) {
        val newCardNumber = runCatching { CardNumberUiModel(number) }.getOrNull() ?: return
        val updatedCard = uiState.card.copy(cardNumberUiModel = newCardNumber)
        uiState = uiState.copy(card = updatedCard)
    }

    fun updateCardExpirationDate(expirationDate: String) {
        val newCardExpirationDate =
            runCatching { CardExpirationDateUiModel(expirationDate) }.getOrNull() ?: return
        val validatedCardExpirationDate =
            newCardExpirationDate.toValidatedCardExpirationDateUiModel()
        val updatedCard = uiState.card.copy(cardExpirationDateUiModel = validatedCardExpirationDate)
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

    fun isRegistrableCard(): Boolean =
        uiState.card.run {
            cardNumberUiModel.isValid() &&
                cardExpirationDateUiModel.isValid() &&
                cardPasswordUiModel.isValid() &&
                cardCompanyUiModel.isSelect()
        }

    companion object {
        private const val SAVER_UI_STATE_KEY = "uiState"
        private const val SAVER_IS_BOTTOM_SHEET_OPEN_KEY = "isBottomSheetOpen"

        val Saver: Saver<CardRegistrationStateHolder, *> =
            mapSaver(
                save = { holder ->
                    mapOf(
                        SAVER_UI_STATE_KEY to holder.uiState,
                        SAVER_IS_BOTTOM_SHEET_OPEN_KEY to holder.isBottomSheetOpen,
                    )
                },
                restore = { map ->
                    val uiState = map[SAVER_UI_STATE_KEY] as CardRegistrationScreenUiState
                    val isBottomSheetOpen = map[SAVER_IS_BOTTOM_SHEET_OPEN_KEY] as Boolean
                    CardRegistrationStateHolder(
                        uiState = uiState,
                        isBottomSheetOpen = isBottomSheetOpen,
                    )
                },
            )
    }
}
