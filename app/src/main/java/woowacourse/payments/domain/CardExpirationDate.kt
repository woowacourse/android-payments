package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
data class CardExpirationDate(
    val month: String = "",
    val year: String = "",
) : Parcelable {
    init {
        require(month.length <= DATE_LENGTH)
        require(year.length <= DATE_LENGTH)
    }

    fun toCombinedFormat(): String = month + year

    fun toFormattedString(separator: String): String = month + separator + year

    fun isValid(): Boolean {
        if (month.isBlank() && year.isBlank()) return true
        if (month.length != DATE_LENGTH || year.length != DATE_LENGTH) return false
        val numberYear = year.toInt() + YEAR_CENTURY_BASE
        val numberMonth = month.toInt()
        val expirationMonth =
            runCatching {
                YearMonth.of(numberYear, numberMonth)
            }.getOrNull() ?: return false
        val today = YearMonth.now()
        return !expirationMonth.isBefore(today)
    }

    companion object {
        const val DATE_INPUT_MAX_LENGTH = 4
        const val DATE_LENGTH = 2
        const val MONTH_INDEX = 0
        const val YEAR_INDEX = 1
        const val YEAR_CENTURY_BASE = 2000

        fun fromRawInput(date: String): CardExpirationDate {
            val parts = date.filter { it.isDigit() }.take(DATE_INPUT_MAX_LENGTH).chunked(DATE_LENGTH)
            return CardExpirationDate(
                month = parts.getOrNull(MONTH_INDEX) ?: "",
                year = parts.getOrNull(YEAR_INDEX) ?: "",
            )
        }
    }
}
