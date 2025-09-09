package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.YearMonth

@Parcelize
@JvmInline
value class ExpirationDate(
    val value: YearMonth
) : Parcelable {
    init {
        require(value.monthValue in 1..12) { "월은 1~12 사이입니다." }
        require(value.year >= LocalDate.now().year) { "년은 현재 년도 이후입니다." }
        require(value.year <= LocalDate.now().year + 5) { "년은 현재 년도 이후 5년 이내입니다." }
    }
}

