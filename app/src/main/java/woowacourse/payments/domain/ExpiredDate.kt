package woowacourse.payments.domain

import java.time.LocalDate

class ExpiredDate private constructor(
    val month: Int,
    val year: Int,
) {
    companion object {
        fun of(
            month: Int,
            year: Int,
            now: LocalDate = LocalDate.now(),
        ): ExpiredDate? {
            val currentYear = now.year - YEAR_TWO_THOUSAND
            val currentMonth = now.monthValue

            if ((year == currentYear && month in currentMonth..MAX_MONTH) || (year > currentYear && month in MIN_MONTH..MAX_MONTH)) {
                return ExpiredDate(month, year)
            }
            return null
        }

        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val YEAR_TWO_THOUSAND = 2000
    }
}
