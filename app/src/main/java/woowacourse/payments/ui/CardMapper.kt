package woowacourse.payments.ui

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.Expired

fun formatCardNumber(cardNumber: CardNumber?): String {
    val raw = cardNumber?.value.orEmpty()
    if (raw.isEmpty()) return ""

    val front = raw.take(8).chunked(4).joinToString(" - ")

    return if (raw.length <= 8) {
        front
    } else {
        val maskedCount = raw.length - 8
        val masked = "*".repeat(maskedCount).chunked(4).joinToString(" - ")
        "$front - $masked"
    }
}

fun formatExpired(expired: Expired?): String {
    if (expired == null || expired.value.isEmpty()) return ""
    return expired.value.chunked(2).joinToString(" / ")
}
