package woowacourse.payments.domain

import java.time.YearMonth

data class PaymentCard(
    val number: Long,
    val expirationDate: YearMonth,
    val cardholderName: String,
) {
    constructor(number: Long, expirationDate: String, cardholderName: String) : this(
        number,
        parseExpirationDate(expirationDate),
        cardholderName
    )

    constructor(number: String, expirationDate: String, cardholderName: String) : this(
        number.toLong(),
        parseExpirationDate(expirationDate),
        cardholderName
    )

    private var password: String = ""

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    companion object {
        private fun parseExpirationDate(expirationDate: String): YearMonth {
            val month = expirationDate.substring(0, 2)
            val year = expirationDate.substring(2, 4)
            return YearMonth.of(year.toInt(), month.toInt())
        }
    }
}
