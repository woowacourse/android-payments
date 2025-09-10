package woowacourse.payments.domain

import java.time.YearMonth

data class PaymentCard(
    val number: Long,
    val expirationDate: YearMonth,
    val cardholderName: String,
) {
    constructor(number: String, expirationDate: String, cardholderName: String) : this(
        number.toLong(),
        parseExpirationDate(expirationDate),
        cardholderName
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PaymentCard) return false
        return number == other.number && expirationDate == other.expirationDate
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + expirationDate.hashCode()
        return result
    }

    companion object {
        private fun parseExpirationDate(expirationDate: String): YearMonth {
            val month = expirationDate.substring(0, 2)
            val year = expirationDate.substring(2, 4)
            return YearMonth.of(year.toInt(), month.toInt())
        }
    }
}
