package woowacourse.payments.domain

import java.time.YearMonth

data class ExpirationDate(
    val value: YearMonth,
    val currentYearMonth: YearMonth = YearMonth.now(),
) {
    init {
        require(value > currentYearMonth) { IllegalArgumentException() }
    }
}
