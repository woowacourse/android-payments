package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val bankUiModel: BankUiModel,
    val number: String,
    val expired: String,
    val owner: String,
) : Parcelable

fun CardUiModel.formattedNumber(): String {
    val front = number.take(8).chunked(4).joinToString(" - ")
    return if (number.length <= 8) {
        front
    } else {
        val maskedCount = (number.length - 8).coerceAtLeast(0)
        val masked = "*".repeat(maskedCount).chunked(4).joinToString(" - ")
        "$front - $masked"
    }
}

fun CardUiModel.formattedExpired(): String = expired.chunked(2).joinToString(" / ")
