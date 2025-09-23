package woowacourse.payments.cardaddition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.BankType
import java.lang.Character.isDigit
import java.time.Month

class CardAdditionStateHolder(
    initialState: CardAdditionUiState = CardAdditionUiState(),
) {
    var uiState: CardAdditionUiState by mutableStateOf(initialState)
        private set

    fun updateCardNumber(value: String) {
        val newCardNumber: String = value.filter(::isDigit).take(CARD_NUMBER_LENGTH)

        uiState =
            uiState.copy(
                cardNumber = newCardNumber,
                isValidCardNumber = newCardNumber.isValidCardNumber,
                canAddCard = canAddCard(isValidCardNumber = newCardNumber.isValidCardNumber),
            )
    }

    fun updateExpiredDate(value: String) {
        val newDate: String = value.filter(::isDigit).take(EXPIRED_DATE_LENGTH)

        uiState =
            uiState.copy(
                expiredDate = newDate,
                isValidExpiredDate = newDate.isValidExpiredDate,
                canAddCard = canAddCard(isValidExpiredDate = newDate.isValidExpiredDate),
            )
    }

    fun updateHolder(value: String) {
        uiState = uiState.copy(holder = value.take(uiState.holderMaxLength).uppercase())
    }

    fun updatePassword(value: String) {
        val newPassword: String = value.filter(::isDigit).take(PASSWORD_LENGTH)

        uiState =
            uiState.copy(
                password = newPassword,
                isValidPassword = newPassword.isValidPassword,
                canAddCard = canAddCard(isValidPassword = newPassword.isValidPassword),
            )
    }

    fun updateBankType(value: BankType?) {
        uiState =
            uiState.copy(
                bankType = value,
                isBankSelected = value.isBankSelected,
                canAddCard = canAddCard(isBankSelected = value.isBankSelected),
            )
    }

    private val String.isValidCardNumber: Boolean get() = length == CARD_NUMBER_LENGTH

    private val String.isValidExpiredDate: Boolean
        get() {
            if (length != EXPIRED_DATE_LENGTH) return false

            val month: Int = take(2).toIntOrNull() ?: return false

            return runCatching { Month.of(month) }.isSuccess
        }

    private val String.isValidPassword: Boolean get() = length == PASSWORD_LENGTH

    private val BankType?.isBankSelected: Boolean get() = this != null

    private fun canAddCard(
        isValidCardNumber: Boolean = uiState.isValidCardNumber,
        isValidExpiredDate: Boolean = uiState.isValidExpiredDate,
        isValidPassword: Boolean = uiState.isValidPassword,
        isBankSelected: Boolean = uiState.isBankSelected,
    ) = isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected

    companion object {
        private const val CARD_NUMBER_LENGTH: Int = 16
        private const val EXPIRED_DATE_LENGTH: Int = 4
        private const val PASSWORD_LENGTH: Int = 4

        val Saver: Saver<CardAdditionStateHolder, CardAdditionUiState> =
            Saver(
                save = { stateHolder: CardAdditionStateHolder -> stateHolder.uiState },
                restore = { state: CardAdditionUiState -> CardAdditionStateHolder(state) },
            )
    }
}
