package woowacourse.payments.ui.text

object CardNumberFormatter {
    const val CARD_NUMBER_CHUNK_SIZE = 4
    const val CARD_NUMBER_SEPARATOR = " - "

    private const val START_MASK_INDEX = 8
    private const val END_MASK_INDEX = 16
    private const val MASK_CHARACTER = "*"

    fun formatAndMask(raw: String): String {
        val digits = raw.filter(Char::isDigit)
        if (digits.isEmpty()) return ""
        val start = START_MASK_INDEX.coerceAtMost(digits.length)
        val end = END_MASK_INDEX.coerceAtMost(digits.length)

        val masked =
            buildString {
                append(digits.substring(0, start))
                if (end > start) append(MASK_CHARACTER.repeat(end - start))
                if (digits.length > end) append(digits.substring(end))
            }

        return masked
            .chunked(CARD_NUMBER_CHUNK_SIZE)
            .joinToString(CARD_NUMBER_SEPARATOR)
    }
}
