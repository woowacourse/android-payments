package woowacourse.payments.domain

class Card(
    val cardNumber: CardNumber,
    val expiredDate: ExpiredDate,
    val ownerName: OwnerName,
    val password: Password,
)
