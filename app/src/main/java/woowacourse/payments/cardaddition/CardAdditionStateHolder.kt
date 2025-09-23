package woowacourse.payments.cardaddition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
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
        val newCardNumber: String = value.filter(::isDigit).take(CARD_NUMBER_LENGTH)

        uiState = uiState.copy(cardNumber = newCardNumber)
    }

    fun updateExpiredDate(value: String) {
        val newExpiredDate: String = value.filter(::isDigit).take(EXPIRED_DATE_LENGTH)

        uiState = uiState.copy(expiredDate = newExpiredDate)
    }

    fun updateHolder(value: String) {
        val holder: String = value.take(uiState.holderMaxLength).uppercase()

        uiState = uiState.copy(holder = holder)
    }

    fun updatePassword(value: String) {
        val newPassword: String = value.filter(::isDigit).take(PASSWORD_LENGTH)

        uiState = uiState.copy(password = newPassword)
    }

    fun updateBankType(newBankType: BankType?) {
        uiState = uiState.copy(bankType = newBankType)
    }
}

@Composable
fun rememberCardAdditionStateHolder(initialState: CardAdditionUiState = CardAdditionUiState()) =
    rememberSaveable(
        saver =
            Saver(
                save = { stateHolder: CardAdditionStateHolder -> stateHolder.uiState },
                restore = { state: CardAdditionUiState -> CardAdditionStateHolder(state) },
            ),
    ) { CardAdditionStateHolder(initialState) }
