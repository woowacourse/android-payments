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
        uiState =
            uiState.copy(
                cardNumber = newCardNumber,
                card = uiState.card.copy(cardNumberUiModel = newCardNumber),
            )
    }

    fun updateCardExpirationDate(expirationDate: String) {
        val newCardExpirationDate =
            runCatching { CardExpirationDateUiModel(expirationDate) }.getOrNull() ?: return
        val validatedCardExpirationDate =
            newCardExpirationDate.toValidatedCardExpirationDateUiModel()
        uiState =
            uiState.copy(
                cardExpirationDate = validatedCardExpirationDate,
                card = uiState.card.copy(cardExpirationDateUiModel = validatedCardExpirationDate),
            )
    }

    fun updateCardholderName(name: String) {
        val newCardholderName =
            runCatching { CardholderNameUiModel(name.uppercase()) }.getOrNull() ?: return
        uiState =
            uiState.copy(
                cardholderName = newCardholderName,
                card = uiState.card.copy(cardholderNameUiModel = newCardholderName),
            )
    }

    fun updatePassword(password: String) {
        val newPassword = runCatching { CardPasswordUiModel(password) }.getOrNull() ?: return
        uiState = uiState.copy(cardPassword = newPassword)
    }

    fun updateCardCompany(cardCompany: CardCompanyUiModel) {
        if (cardCompany == CardCompanyUiModel.NOT_SELECT) return
        uiState =
            uiState.copy(
                cardCompany = cardCompany,
                card = uiState.card.copy(cardCompanyUiModel = cardCompany),
            )
    }

    fun isRegistrableCard(): Boolean =
        uiState.run {
            cardNumber.isValid() &&
                cardExpirationDate.isValid() &&
                cardPassword.isValid() &&
                cardCompany.isSelect()
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
