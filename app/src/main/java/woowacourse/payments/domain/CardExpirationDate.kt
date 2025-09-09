package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Parcelize
@JvmInline
value class CardExpirationDate(
    val date: YearMonth,
) : Parcelable {
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
