package woowacourse.payments.domain

import java.time.YearMonth

@JvmInline
value class CardExpiry(val value: YearMonth) {
    init {
        require(value.isAfter(MINIMUM_VALID_EXPIRY) || value == MINIMUM_VALID_EXPIRY) { "카드 만료일은 $MINIMUM_VALID_EXPIRY 이후여야 합니다." }
    }

    companion object {
        private val MINIMUM_VALID_EXPIRY = YearMonth.now()

        fun fromString(raw: String): CardExpiry {
            require(raw.length == 4 && raw.all { it.isDigit() }) {
                "만료일은 4자리 숫자(MM / YY)여야 합니다."
            }
            val month = ExpiryMonth(raw.substring(0, 2).toInt())
            val year = ExpiryYear(raw.substring(2, 4).toInt())
            return CardExpiry(YearMonth.of(year.toFourDigitYear(), month.value))
        }
    }
}
