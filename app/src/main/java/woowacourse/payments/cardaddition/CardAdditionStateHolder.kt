package woowacourse.payments.cardaddition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.cardaddition.CardAdditionUiState.Companion.CARD_NUMBER_LENGTH
import woowacourse.payments.cardaddition.CardAdditionUiState.Companion.CARD_OWNER_NAME_LENGTH_MAX
import woowacourse.payments.cardaddition.CardAdditionUiState.Companion.EXPIRED_DATE_LENGTH
import woowacourse.payments.cardaddition.CardAdditionUiState.Companion.PASSWORD_LENGTH
import java.lang.Character.isDigit

class CardAdditionStateHolder(
    initialState: CardAdditionUiState = CardAdditionUiState(),
) {
    var uiState: CardAdditionUiState by mutableStateOf(initialState)
        private set

    fun updateCardNumber(value: String) {
        val newCardNumber: String = value.filter(::isDigit)
        uiState = uiState.copy(cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH))
    }

    fun updateExpiredDate(newValue: String) {
        val newDate: String = newValue.filter(::isDigit)
        uiState = uiState.copy(expiredDate = newDate.take(EXPIRED_DATE_LENGTH))
    }

    fun updateHolder(newValue: String) {
        uiState = uiState.copy(holder = newValue.take(CARD_OWNER_NAME_LENGTH_MAX).uppercase())
    }

    fun updatePassword(newValue: String) {
        val newPassword: String = newValue.filter(::isDigit)
        uiState = uiState.copy(password = newPassword.take(PASSWORD_LENGTH))
    }

    companion object {
        val Saver: Saver<CardAdditionStateHolder, CardAdditionUiState> =
            Saver(
                save = { stateHolder: CardAdditionStateHolder -> stateHolder.uiState },
                restore = { state: CardAdditionUiState -> CardAdditionStateHolder(state) },
            )
    }
}
