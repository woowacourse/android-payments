package woowacourse.payments.domain

data class Card(
    val company: CardCompany = CardCompany.NOT_SELECTED,
    val number: CardNumber,
    val expiry: CardExpiry,
    val password: CardPassword,
    val name: CardName = CardName(null),
)
