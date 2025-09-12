package woowacourse.payments.domain.card

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
