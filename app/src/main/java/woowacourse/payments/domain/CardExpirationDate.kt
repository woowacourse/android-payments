package woowacourse.payments.domain

import java.time.YearMonth

data class CardExpirationDate(
    val date: YearMonth,
) {
    fun isExpired(now: YearMonth = YearMonth.now()): Boolean = date.isBefore(now)
}
