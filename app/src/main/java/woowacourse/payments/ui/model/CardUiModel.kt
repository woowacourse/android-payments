package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class CardUiModel(
    val number: String,
    val expired: String,
    val owner: String,
) : Parcelable {
    val maskedNumber get() = formatCardNumber(number)
    val formattedExpired get() = formatExpired(expired)
}

fun formatCardNumber(cardNumber: String): String {
    val front = cardNumber.take(8).chunked(4).joinToString(" - ")

    return if (cardNumber.length <= 8) {
        front
    } else {
        val maskedCount = (cardNumber.length - 8).coerceAtLeast(0)
        val masked = "*".repeat(maskedCount).chunked(4).joinToString(" - ")
        "$front - $masked"
    }
}

fun formatExpired(expired: String): String = expired.chunked(2).joinToString(" / ")

fun Card.toPresentation(): CardUiModel =
    CardUiModel(
        number = number.value,
        expired = expired.value,
        owner = owner.value,
    )
