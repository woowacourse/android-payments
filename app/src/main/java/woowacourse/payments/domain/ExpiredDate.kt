package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class ExpiredDate(val value: String) {
    val formattedDate: Result<YearMonth>
        get() = runCatching { YearMonth.parse(value, formatter) }

    val isValid: Boolean
        get() = formattedDate.isSuccess && formattedDate.getOrThrow()
            .isBefore(YearMonth.now()).not()

    companion object {
        private const val PATTERN = "MMyy"
        private val formatter = DateTimeFormatter.ofPattern(PATTERN)
    }
}