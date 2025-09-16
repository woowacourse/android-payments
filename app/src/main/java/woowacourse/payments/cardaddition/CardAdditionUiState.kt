package woowacourse.payments.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.lang.Character.isDigit
import java.time.Month

@Parcelize
data class CardAdditionUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val holder: String = "",
    val holderMaxLength: Int = 30,
    val password: String = "",
) : Parcelable {
    fun newCardNumber(newValue: String): CardAdditionUiState {
        val newCardNumber: String = newValue.filter(::isDigit)
        return copy(cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH))
    }

    fun newExpiredDate(newValue: String): CardAdditionUiState {
        val newDate: String = newValue.filter(::isDigit)
        return copy(expiredDate = newDate.take(EXPIRED_DATE_LENGTH))
    }

    fun newHolder(newValue: String): CardAdditionUiState = copy(holder = newValue.take(CARD_OWNER_NAME_LENGTH_MAX).uppercase())

    fun newPassword(newValue: String): CardAdditionUiState {
        val newPassword: String = newValue.filter(::isDigit)
        return copy(password = newPassword.take(PASSWORD_LENGTH))
    }

    val isValid: Boolean get() = isValidCardNumber && isValidExpiredDate && isValidPassword

    val isValidCardNumber: Boolean get() = cardNumber.length == CARD_NUMBER_LENGTH
    val isValidExpiredDate: Boolean
        get() {
            val month: Int = expiredDate.take(2).toIntOrNull() ?: return false
            return expiredDate.length == EXPIRED_DATE_LENGTH && month in Month.entries.map(Month::getValue)
        }

    val isValidPassword: Boolean get() = password.length == PASSWORD_LENGTH

    companion object {
        private const val CARD_NUMBER_LENGTH: Int = 16
        private const val PASSWORD_LENGTH: Int = 4
        private const val EXPIRED_DATE_LENGTH: Int = 4
        private const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30
    }
}
