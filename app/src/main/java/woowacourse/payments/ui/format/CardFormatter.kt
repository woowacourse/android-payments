package woowacourse.payments.ui.format

fun String.maskedCardNumber(separator: String): String {
    val raw = filter { it.isDigit() }
    if (raw.length <= 8) return raw
    val masked = raw.take(8) + "*".repeat(raw.length - 8)
    return masked.chunked(4).joinToString(separator)
}

fun String.formattedExpiry(separator: String): String {
    val raw = filter { it.isDigit() }
    return raw.chunked(2).joinToString(separator)
}
