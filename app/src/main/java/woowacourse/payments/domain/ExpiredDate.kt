package woowacourse.payments.domain

import java.time.LocalDate

class ExpiredDate private constructor(
    val month: Int,
    val year: Int
) {
    companion object {
        fun of(month: Int, year: Int, now: LocalDate = LocalDate.now()): ExpiredDate? {
            if (month in MIN_MONTH..MAX_MONTH && year >= now.year)
                return ExpiredDate(month, year)
            return null
        }

        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
    }
}
