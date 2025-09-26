package woowacourse.payments.domain

data class Card(
    val id: Long = System.currentTimeMillis(),
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val cardholderName: CardholderName,
    val passcode: Passcode,
    val cardCompany: CardCompany,
)
