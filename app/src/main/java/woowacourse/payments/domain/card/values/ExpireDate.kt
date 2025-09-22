package woowacourse.payments.domain.card.values

import woowacourse.payments.domain.card.exception.ExpireDateException
import woowacourse.payments.ui.model.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import java.time.YearMonth

@JvmInline
value class ExpireDate private constructor(
    val value: YearMonth,
) {
    init {
        if (value < YearMonth.now()) {
            throw ExpireDateException(ExpireDateInvalidReason.EXPIRED)
        }
    }

    companion object {
        fun create(value: YearMonth): Result<ExpireDate> = runCatching { ExpireDate(value) }
    }
}
