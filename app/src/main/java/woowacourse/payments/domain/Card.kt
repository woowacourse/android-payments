package woowacourse.payments.domain

data class Card(
    val id: Long?,
    val cardholderName: String?,
    val cardNumber: String,
    val cardPassword: String,
    val cardCompany: CardCompany,
    val cardExpirationDate: CardExpirationDate,
)
