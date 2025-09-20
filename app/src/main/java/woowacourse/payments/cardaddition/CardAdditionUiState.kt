package woowacourse.payments.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
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
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = cardNumber.length == CARD_NUMBER_LENGTH

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean =
        run {
            if (expiredDate.length != EXPIRED_DATE_LENGTH) return@run false

            val month: Int = expiredDate.take(2).toIntOrNull() ?: return@run false

            runCatching { Month.of(month) }.isSuccess
        }

    @IgnoredOnParcel
    val isValidPassword: Boolean = password.length == PASSWORD_LENGTH

    @IgnoredOnParcel
    val isBankSelected: Boolean = bankType != null

    @IgnoredOnParcel
    val isValid: Boolean =
        isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected

    companion object {
        const val CARD_NUMBER_LENGTH: Int = 16
        const val EXPIRED_DATE_LENGTH: Int = 4
        const val PASSWORD_LENGTH: Int = 4
    }
}
