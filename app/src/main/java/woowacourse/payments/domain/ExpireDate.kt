package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import java.time.YearMonth

@JvmInline
@Parcelize
value class ExpireDate private constructor(
    val value: YearMonth,
) : Parcelable {
    init {
        if (value < YearMonth.now()) {
            throw ExpireDateValidationException(ExpireDateInvalidReason.EXPIRED)
        }
    }

    companion object {
        const val MAX_LENGTH_EXPIRE_DATE = 4

        fun create(value: YearMonth): Result<ExpireDate> = runCatching { ExpireDate(value) }

        fun from(expireDateString: String): Result<ExpireDate> {
            if (expireDateString.length != MAX_LENGTH_EXPIRE_DATE) {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.INVALID_FORMAT))
            }

            val mm =
                expireDateString.take(2).toIntOrNull() ?: return Result.failure(
                    ExpireDateValidationException(ExpireDateInvalidReason.INVALID_FORMAT),
                )

            val yy =
                expireDateString.takeLast(2).toIntOrNull() ?: return Result.failure(
                    ExpireDateValidationException(ExpireDateInvalidReason.INVALID_FORMAT),
                )

            if (mm !in 1..12) {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.INVALID_MONTH))
            }

            val yearMonth = YearMonth.of(2000 + yy, mm)

            return create(yearMonth)
        }
    }
}
