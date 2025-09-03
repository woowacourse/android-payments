package woowacourse.payments.domain

import java.time.LocalDate
import java.time.YearMonth

data class CardExpirationDate(
    val month: String,
    val year: String,
) {
    override fun toString(): String = month + year

    fun onValueChange(date: String): CardExpirationDate {
        val parts = date.chunked(2)
        return copy(
            month = parts.getOrNull(0) ?: "",
            year = parts.getOrNull(1) ?: "",
        )
    }

    fun isValid(): Boolean {
        if (month.isBlank() && year.isBlank()) return true
        if (month.length != 2 || year.length != 2) return false
        val numberYear = year.toInt() + 2000
        val numberMonth = month.toInt()
        val expirationMonth =
            runCatching {
                YearMonth.of(2000 + numberYear, numberMonth)
            }.getOrNull() ?: return false
        val expirationEndDate = expirationMonth.atEndOfMonth()
        val today = LocalDate.now()
        return !expirationEndDate.isBefore(today)
    }
}