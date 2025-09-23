package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val id: String,
    val cardNumber: String,
    val expiry: String,
    val owner: String,
    val bank: BankUiModel,
) : Parcelable {
    fun maskedCardNumber(separator: String): String {
        if (cardNumber.length <= 8) return cardNumber
        val masked = cardNumber.take(8) + "*".repeat(cardNumber.length - 8)
        return masked.chunked(4).joinToString(separator)
    }

    fun formattedExpiry(separator: String): String = expiry.chunked(2).joinToString(separator)
}
