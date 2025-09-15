package woowacourse.payments.ui.text

import java.time.YearMonth
import java.time.format.DateTimeFormatterBuilder
import java.time.format.ResolverStyle
import java.time.temporal.ChronoField
import java.util.Locale

object ExpirationDateInputParser {
    private val formatter =
        DateTimeFormatterBuilder()
            .appendPattern("MM")
            .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT)

    fun parse(raw: String): YearMonth {
        val digits = raw.filter(Char::isDigit)
        require(digits.length == 4) { "만료일은 4자리여야 합니다." }
        return YearMonth.parse(digits, formatter)
    }
}
