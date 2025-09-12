package woowacourse.payments.domain.card.values

import woowacourse.payments.domain.card.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.domain.card.exception.ExpireDateException
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
        const val MAX_LENGTH_EXPIRE_DATE = 4

        fun create(value: YearMonth): Result<ExpireDate> = runCatching { ExpireDate(value) }

        fun from(expireDateString: String): Result<ExpireDate> {
            if (expireDateString.length != MAX_LENGTH_EXPIRE_DATE) {
                return Result.failure(ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT))
            }

            val mm =
                expireDateString.take(2).toIntOrNull() ?: return Result.failure(
                    ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT),
                )

            val yy =
                expireDateString.takeLast(2).toIntOrNull() ?: return Result.failure(
                    ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT),
                )

            if (mm !in 1..12) {
                return Result.failure(ExpireDateException(ExpireDateInvalidReason.INVALID_MONTH))
            }

            val yearMonth = YearMonth.of(2000 + yy, mm)

            return create(yearMonth)
        }
    }
}
