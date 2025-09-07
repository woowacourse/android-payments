package woowacourse.payments.domain

import java.time.YearMonth

typealias ExpireDateInvalidReason = ExpireDateStatus.Invalid.Reason

sealed interface ExpireDateStatus {
    data class Valid(
        val yearMonth: YearMonth,
    ) : ExpireDateStatus

    data object Typing : ExpireDateStatus

    data class Invalid(
        val reason: Reason,
    ) : ExpireDateStatus {
        enum class Reason {
            INVALID_FORMAT,
            EXPIRED,
            INVALID_MONTH,
        }
    }
}
