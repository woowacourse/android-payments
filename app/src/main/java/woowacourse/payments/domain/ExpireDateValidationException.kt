package woowacourse.payments.domain

class ExpireDateValidationException(
    val reason: ExpireDateInvalidReason,
) : IllegalArgumentException()
