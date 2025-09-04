package woowacourse.payments.domain

import java.time.YearMonth

data class ExpirationDate(
    val expirationYearMonth: YearMonth,
    val currentYearMonth: YearMonth,
) {
    init {
        require(expirationYearMonth > currentYearMonth) { IllegalArgumentException() }
    }
}
