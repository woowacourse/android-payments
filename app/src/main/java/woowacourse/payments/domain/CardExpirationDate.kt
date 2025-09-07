package woowacourse.payments.domain

import java.time.LocalDate
import java.time.YearMonth

data class CardExpirationDate(
    val month: String = "",
    val year: String = "",
) {
    fun toCombinedFormat(): String = month + year

    fun onValueChange(date: String): CardExpirationDate {
        val parts = date.filter { it.isDigit() }.take(DATE_INPUT_MAX_LENGTH).chunked(DATE_LENGTH)
        return copy(
            month = parts.getOrNull(MONTH_INDEX) ?: "",
            year = parts.getOrNull(YEAR_INDEX) ?: "",
        )
    }

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
    }
}
