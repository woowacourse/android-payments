package woowacourse.payments.domain

data class Card(
    val id: Long,
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val cardholderName: CardholderName,
    val passcode: Passcode,
    val cardCompany: CardCompany,
)
