package woowacourse.payments.cardediting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.BankType
import woowacourse.payments.Card
import java.lang.Character.isDigit

class CardEditingStateHolder(
    initialState: CardEditingUiState,
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun updateCardNumber(value: String) {
        val newCardNumber = value.filter(::isDigit).take(Card.NUMBER_LENGTH)

        uiState = uiState.copy(edited = uiState.edited.copy(number = newCardNumber))
    }

    fun updateExpiredDate(value: String) {
        val newExpiredDate = value.filter(::isDigit).take(Card.EXPIRED_DATE_LENGTH)
        uiState = uiState.copy(edited = uiState.edited.copy(expiredDate = newExpiredDate))
    }

    fun updateHolder(value: String) {
        val holder = value.take(uiState.edited.holderMaxLength).uppercase()

        uiState = uiState.copy(edited = uiState.edited.copy(holder = holder))
    }

    fun updatePassword(value: String) {
        val newPassword = value.filter(::isDigit).take(Card.PASSWORD_LENGTH)

        uiState = uiState.copy(edited = uiState.edited.copy(password = newPassword))
    }

    fun updateBankType(newBankType: BankType?) {
        uiState = uiState.copy(edited = uiState.edited.copy(bankType = newBankType))
    }
}

@Composable
fun rememberCardEditingStateHolder(initialState: CardEditingUiState) =
    rememberSaveable(
        saver =
            Saver(
                save = { it.uiState },
                restore = { CardEditingStateHolder(it) },
            ),
    ) { CardEditingStateHolder(initialState) }
