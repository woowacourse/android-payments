package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class CardExpirationDate(
    val date: YearMonth,
) {
    init {
        require(!isExpired()) { ERROR_EXPIRED_CARD_EXPIRATION_DATE }
    }

    fun isExpired(now: YearMonth = YearMonth.now()): Boolean = date.isBefore(now)

    companion object {
        private const val ERROR_EXPIRED_CARD_EXPIRATION_DATE = "만료된 카드입니다."

        fun from(
            date: String,
            formatter: DateTimeFormatter,
        ): CardExpirationDate =
            CardExpirationDate(
                YearMonth.parse(
                    date.replace(" ", ""),
                    formatter,
                ),
            )
    }
}
