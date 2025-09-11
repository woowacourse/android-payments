package woowacourse.payments.domain

import java.time.YearMonth

sealed interface ExpireDateStatus {
    data class Invalid(
        val reason: ExpireDateInvalidReason,
    ) : ExpireDateStatus {
        enum class ExpireDateInvalidReason {
            INVALID_FORMAT,
            EXPIRED,
            INVALID_MONTH,
        }
    }
}
