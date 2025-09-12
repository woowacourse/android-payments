package woowacourse.payments.domain.card.exception

import woowacourse.payments.domain.card.ExpireDateStatus

class ExpireDateException(
    val reason: ExpireDateStatus.Invalid.ExpireDateInvalidReason,
) : IllegalArgumentException()
