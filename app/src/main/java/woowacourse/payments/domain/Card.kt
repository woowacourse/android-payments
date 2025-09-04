package woowacourse.payments.domain

import java.time.YearMonth

data class Card(
    val cardNumber: String,
    val expireDate: YearMonth,
    val ownerName: String?,
    val password: String,
)
