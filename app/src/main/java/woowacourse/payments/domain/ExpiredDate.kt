package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class ExpiredDate(val date: String) {
    val formattedDate: Result<YearMonth> by lazy {
        runCatching { YearMonth.parse(date, formatter) }
    }

    val isValid: Boolean by lazy {
        date.length == DATE_LENGTH && formattedDate.isSuccess && formattedDate.getOrThrow()
            .isBefore(YearMonth.now()).not()
    }

    companion object {
        private const val DATE_LENGTH = 4
        private const val PATTERN = "MMyy"
        private val formatter = DateTimeFormatter.ofPattern(PATTERN)
    }
}