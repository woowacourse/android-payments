package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class CardExpirationDate(
    val date: YearMonth,
) {
    fun isExpired(now: YearMonth = YearMonth.now()): Boolean = date.isBefore(now)

    companion object {
        fun from(
            value: String,
            formatter: DateTimeFormatter,
        ): CardExpirationDate {
            val date: String = value.filter(Char::isDigit)
            return CardExpirationDate(YearMonth.parse(date, formatter))
        }
    }
}
