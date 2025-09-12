package woowacourse.payments.domain.model

import java.time.YearMonth
import java.time.format.DateTimeFormatterBuilder
import java.time.format.ResolverStyle
import java.time.temporal.ChronoField
import java.util.Locale

data class ExpirationDate(
    val value: YearMonth,
    val currentYearMonth: YearMonth = YearMonth.now(),
) {
    init {
        require(value >= currentYearMonth) { "만료일은 현재 연월보다 이후여야 합니다." }
    }

    companion object {
        private val formatter =
            DateTimeFormatterBuilder()
                .appendPattern("MM")
                .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
                .toFormatter(Locale.ROOT)
                .withResolverStyle(ResolverStyle.STRICT)

        fun from(raw: String): ExpirationDate {
            val ym = YearMonth.parse(raw, formatter)
            return ExpirationDate(ym)
        }
    }
}
