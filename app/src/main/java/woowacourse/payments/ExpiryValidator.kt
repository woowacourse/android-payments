package woowacourse.payments

import java.time.LocalDate

object ExpiryValidator {
    fun isValidExpiry(input: String): Boolean {
        if (input.length != 4) return false
        val month = input.take(2).toIntOrNull() ?: return false
        val year = input.takeLast(2).toIntOrNull() ?: return false

        if (month !in 1..12) return false

        val currentYear = LocalDate.now().year
        val currentMonth = LocalDate.now().monthValue

        val expiryYear = 2000 + year
        val expiryMonth = month

        return expiryYear > currentYear ||
            (expiryYear == currentYear && expiryMonth >= currentMonth)
    }
}
