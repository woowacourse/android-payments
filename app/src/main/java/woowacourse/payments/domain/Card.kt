package woowacourse.payments.domain

data class Card(
    val number: CardNumber = CardNumber("", "", "", ""),
    val expirationDate: CardExpirationDate = CardExpirationDate("", ""),
    val ownerName: String = "",
    val password: String = "",
)
