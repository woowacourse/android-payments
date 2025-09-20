package woowacourse.payments.domain.parser

import java.time.YearMonth
import java.time.format.DateTimeFormatterBuilder
import java.time.format.ResolverStyle
import java.time.temporal.ChronoField
import java.util.Locale

object ExpirationDateParser {
    private const val EXPIRATION_DATE_LENGTH = 4

    private val formatter =
        DateTimeFormatterBuilder()
            .appendPattern("MM")
            .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT)

    fun parse(raw: String): YearMonth? {
        val digits = raw.filter(Char::isDigit)
        if (digits.length != EXPIRATION_DATE_LENGTH) return null
        return runCatching { YearMonth.parse(digits, formatter) }.getOrNull()
    }
}
