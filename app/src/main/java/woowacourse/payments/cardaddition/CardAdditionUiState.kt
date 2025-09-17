package woowacourse.payments.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.BankType
import java.time.Month

@Parcelize
data class CardAdditionUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val holder: String = "",
    val holderMaxLength: Int = 30,
    val password: String = "",
    val bankType: BankType? = null,
) : Parcelable {
    val isValid: Boolean get() = isValidCardNumber && isValidExpiredDate && isValidPassword

    val isValidCardNumber: Boolean get() = cardNumber.length == CARD_NUMBER_LENGTH

    val isValidExpiredDate: Boolean
        get() {
            val month: Int = expiredDate.take(2).toIntOrNull() ?: return false
            return expiredDate.length == EXPIRED_DATE_LENGTH && month in Month.entries.map(Month::getValue)
        }

    val isValidPassword: Boolean get() = password.length == PASSWORD_LENGTH

    val isBankSelected: Boolean get() = bankType != null

    companion object {
        const val CARD_NUMBER_LENGTH: Int = 16
        const val EXPIRED_DATE_LENGTH: Int = 4
        const val PASSWORD_LENGTH: Int = 4
        const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30
    }
}
