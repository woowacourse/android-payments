package woowacourse.payments.domain.model

data class Card(
    val type: BankType,
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val userName: UserName,
    val password: Password,
)
