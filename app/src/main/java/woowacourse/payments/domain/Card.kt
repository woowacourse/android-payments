package woowacourse.payments.domain

data class Card(
    val number: CardNumber,
    val holderName: CardholderName,
    val expirationDate: ExpirationDate,
    val passcode: Passcode,
)
