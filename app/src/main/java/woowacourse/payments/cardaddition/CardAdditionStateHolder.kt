package woowacourse.payments.cardaddition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.BankType
import woowacourse.payments.cardaddition.CardAdditionUiState.Companion.CARD_NUMBER_LENGTH
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

    fun updateExpiredDate(value: String) {
        val newDate: String = value.filter(::isDigit)
        uiState = uiState.copy(expiredDate = newDate.take(EXPIRED_DATE_LENGTH))
    }

    fun updateHolder(value: String) {
        uiState = uiState.copy(holder = value.take(uiState.holderMaxLength).uppercase())
    }

    fun updatePassword(value: String) {
        val newPassword: String = value.filter(::isDigit)
        uiState = uiState.copy(password = newPassword.take(PASSWORD_LENGTH))
    }

    fun updateBankType(bankType: BankType?) {
        uiState = uiState.copy(bankType = bankType)
    }

    companion object {
        val Saver: Saver<CardAdditionStateHolder, CardAdditionUiState> =
            Saver(
                save = { stateHolder: CardAdditionStateHolder -> stateHolder.uiState },
                restore = { state: CardAdditionUiState -> CardAdditionStateHolder(state) },
            )
    }
}
