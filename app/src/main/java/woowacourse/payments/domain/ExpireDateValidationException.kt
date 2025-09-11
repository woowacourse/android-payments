package woowacourse.payments.domain

import woowacourse.payments.domain.ExpireDateStatus.Invalid.ExpireDateInvalidReason

class ExpireDateValidationException(
    val reason: ExpireDateInvalidReason,
) : IllegalArgumentException()
