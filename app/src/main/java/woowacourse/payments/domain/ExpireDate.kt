package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@JvmInline
@Parcelize
value class ExpireDate(
    val value: YearMonth,
) : Parcelable {
    init {
        require(value >= YearMonth.now()) { "만료된 날짜로는 ExpireDate 객체를 생성할 수 없습니다." }
    }

    companion object {
        const val MAX_LENGTH_EXPIRE_DATE = 4

        fun from(expireDateString: String): Result<ExpireDate> {
            if (expireDateString.length != MAX_LENGTH_EXPIRE_DATE) {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.INVALID_FORMAT))
            }

            val mm = expireDateString.take(2).toIntOrNull()
            val yy = expireDateString.drop(2).toIntOrNull()

            if (mm == null || yy == null) {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.INVALID_FORMAT))
            }

            if (mm !in 1..12) {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.INVALID_MONTH))
            }

            val yearMonth = YearMonth.of(2000 + yy, mm)

            runCatching { ExpireDate(yearMonth) }.getOrElse {
                return Result.failure(ExpireDateValidationException(ExpireDateInvalidReason.EXPIRED))
            }
            return Result.success(ExpireDate(yearMonth))
        }
    }
}
