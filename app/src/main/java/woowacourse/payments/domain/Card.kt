package woowacourse.payments.domain

data class Card(
    val bankType: BankType,
    val cardNumbers: String,
    val cardExpiry: String,
    val ownerName: String,
    val password: String,
    val id: Long
)