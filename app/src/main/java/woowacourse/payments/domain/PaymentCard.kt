package woowacourse.payments.domain

data class PaymentCard(
    val id: Long,
    val bankType: BankType,
    val number: CardNumber,
    val expirationDate: CardExpirationDate,
    val cardholderName: CardholderName,
    val password: CardPassword,
)
