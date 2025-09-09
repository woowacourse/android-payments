package woowacourse.payments.domain

import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class PaymentCard(
    val number: Long,
    val expirationDate: YearMonth,
    val cardholderName: String,
) {
    constructor(number: Long, expirationDate: String, cardholderName: String) : this(
        number,
        YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yy")),
        cardholderName
    )

    private var password: String = ""

    fun updatePassword(newPassword: String) {
        password = newPassword
    }
}
