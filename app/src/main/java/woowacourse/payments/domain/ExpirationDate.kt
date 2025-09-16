package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class ExpirationDate(
    val expirationDate: String,
) {
    init {
        require(expirationDate.length == 4 && expirationDate.all { it.isDigit() }) {
            "만료일은 4자리 숫자여야 합니다."
        }

        val month = expirationDate.substring(0, 2)
        val year = expirationDate.substring(2, 4)

        val expirationYearMonth =
            runCatching {
                val formatter = DateTimeFormatter.ofPattern("MM/yy")
                YearMonth.parse("$month/$year", formatter)
            }.getOrElse {
                throw IllegalArgumentException("유효하지 않은 날짜 형식입니다.", it)
            }

        require(!expirationYearMonth.isBefore(YearMonth.now())) {
            "만료된 카드입니다."
        }
    }
}
