package woowacourse.payments.domain

data class Card(
    val number: CardNumber,
    val expirationDate: CardExpirationDate,
    val password: CardPassword,
    val holderName: CardHolderName? = null,
)
