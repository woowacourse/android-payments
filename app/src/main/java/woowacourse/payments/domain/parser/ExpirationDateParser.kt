package woowacourse.payments.domain.parser

import woowacourse.payments.domain.model.ExpirationDate.Companion.EXPIRATION_DATE_LENGTH
import java.time.YearMonth
import java.time.format.DateTimeFormatterBuilder
import java.time.format.ResolverStyle
import java.time.temporal.ChronoField
import java.util.Locale

object ExpirationDateParser {
    private val formatter =
        DateTimeFormatterBuilder()
            .appendPattern("MM")
            .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT)

    fun parseOrNull(raw: String): YearMonth? {
        val digits = raw.filter(Char::isDigit)
        if (digits.length != EXPIRATION_DATE_LENGTH) return null
        return runCatching { YearMonth.parse(digits, formatter) }.getOrNull()
    }
}
