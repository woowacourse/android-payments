package woowacourse.payments.domain

class ExpiredDate private constructor(
    val month: Int,
    val year: Int
) {
    companion object {
        fun of(month: Int, year: Int): ExpiredDate? {
            if (month in MIN_MONTH..MAX_MONTH && year >= THIS_YEAR)
                return ExpiredDate(month, year)
            return null
        }

        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val THIS_YEAR = 25
    }
}
