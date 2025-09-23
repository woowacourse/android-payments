package woowacourse.payments.ui.text

object ExpirationDateFormatter {
    private const val EXPIRATION_DATE_SEPARATOR = " / "
    private const val EXPIRATION_CHUNK_SIZE = 2

    fun format(raw: String): String {
        val digits = raw.filter(Char::isDigit)
        if (digits.isEmpty()) return ""
        return when {
            digits.length <= EXPIRATION_CHUNK_SIZE -> digits // "M", "MM"
            else -> {
                val mm = digits.take(EXPIRATION_CHUNK_SIZE)
                val yy = digits.drop(EXPIRATION_CHUNK_SIZE)
                if (yy.isEmpty()) {
                    mm
                } else {
                    listOf(mm, yy).joinToString(EXPIRATION_DATE_SEPARATOR)
                }
            }
        }
    }
}
