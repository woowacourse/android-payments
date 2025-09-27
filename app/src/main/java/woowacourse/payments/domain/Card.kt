package woowacourse.payments.domain

import java.time.YearMonth

data class Card(
    val bankType: BankType,
    val number: CardNumber,
    val expiredDate: YearMonth,
    val password: CardPassword,
    val holder: String? = null,
)
