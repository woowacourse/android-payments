package woowacourse.payments.domain

object CardValidator {
    fun isValidNumber(number: String): Boolean = number.length == 16 && number.all { it.isDigit() }

    fun isValidExpiredDate(expiredDate: String): Boolean {
        if (expiredDate.length != 4 || !expiredDate.all { it.isDigit() }) return false

        val month: Int = expiredDate.take(2).toIntOrNull() ?: return false
        val day: Int = expiredDate.takeLast(2).toIntOrNull() ?: return false

        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> day in 1..31
            4, 6, 9, 11 -> day in 1..30
            2 -> day in 1..29
            else -> false
        }
    }

    fun isValidPassword(password: String): Boolean = password.length == 4 && password.all { it.isDigit() }

    fun isValidCard(
        number: String,
        expiredDate: String,
        password: String,
    ): Boolean = isValidNumber(number) && isValidExpiredDate(expiredDate) && isValidPassword(password)
}
