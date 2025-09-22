package woowacourse.payments.ui

import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun String.toYearMonth(): YearMonth? {
    val yearOffset = 2000
    if (length != 4) return null
    val year = substring(2, 4).toIntOrNull()
    val month = substring(0, 2).toIntOrNull()
    if (month !in 1..12) return null
    return if (year == null || month == null) {
        null
    } else {
        YearMonth.of(yearOffset + year, month)
    }
}

fun YearMonth.toYearMonthString(): String = format(DateTimeFormatter.ofPattern("MM/yy"))
