package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.exception.ExpirationDateException
import java.time.LocalDate
import java.time.YearMonth

@Parcelize
@JvmInline
value class ExpirationDate(
    val value: YearMonth
) : Parcelable {
    init {
        require(value.monthValue in JANUARY..DECEMBER) { ExpirationDateException.ExpirationDateMonthException.message }
        require(value.year >= LocalDate.now().year) { ExpirationDateException.ExpirationDateYearAfterNowException.message }
        require(value.year <= LocalDate.now().year + COMMERCIAL_DEBT_LIMITED_YEAR) { ExpirationDateException.ExpirationDateYearBeforeFiveYearsException.message }
    }

    companion object {
        private const val JANUARY: Int = 1
        private const val DECEMBER: Int = 12
        private const val COMMERCIAL_DEBT_LIMITED_YEAR: Int = 5
    }
}



