package woowacourse.payments.domain

import java.time.YearMonth

data class ExpirationDate(
    val value: YearMonth,
    val currentYearMonth: YearMonth = YearMonth.now(),
) {
    init {
        require(value >= currentYearMonth) { ALREADY_EXPIRED_ERROR_MESSAGE }
    }

    companion object {
        private const val ALREADY_EXPIRED_ERROR_MESSAGE = "만료일은 현재 연월 이후여야 합니다."
    }
}
