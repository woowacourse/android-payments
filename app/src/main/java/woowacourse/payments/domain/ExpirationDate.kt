package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class ExpirationDate(
    val expirationDate: String,
) {
    init {
        val regex = Regex("^(0[1-9]|1[0-2])/([0-9]{2})$")
        require(expirationDate.matches(regex)) {
            "만료일은 'MM/YY' 형식이어야 합니다."
        }

        val expirationYearMonth =
            runCatching {
                val formatter = DateTimeFormatter.ofPattern("MM/yy")
                YearMonth.parse(expirationDate, formatter)
            }.getOrElse {
                throw IllegalArgumentException("유효하지 않은 날짜 형식입니다.", it)
            }

        require(expirationYearMonth.isAfter(YearMonth.now())) {
            "만료된 카드입니다."
        }
    }
}
