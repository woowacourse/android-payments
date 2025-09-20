package woowacourse.payments.domain

import java.time.LocalDate

object CardValidator {
    fun isValidNumber(number: String): Boolean = number.length == 16 && number.all { it.isDigit() }

    fun isValidExpiredDate(expiredDate: String): Boolean {
        if (expiredDate.length != 4 || !expiredDate.all { it.isDigit() }) return false

        val month: Int = expiredDate.take(2).toIntOrNull() ?: return false
        val year: Int = expiredDate.takeLast(2).toIntOrNull() ?: return false

        val currentDate: LocalDate = LocalDate.now()
        val currentYear: Int =
            currentDate.year
                .toString()
                .takeLast(2)
                .toInt()
        val currentMonth: Int = currentDate.month.value

        if (year < currentYear) return false
        if (month !in 1..12) return false
        if (year == currentYear && month < currentMonth) return false
        return true
    }

    fun isValidPassword(password: String): Boolean = password.length == 4 && password.all { it.isDigit() }

    fun isValidCard(
        number: String,
        expiredDate: String,
        password: String,
    ): Boolean = isValidNumber(number) && isValidExpiredDate(expiredDate) && isValidPassword(password)
}
