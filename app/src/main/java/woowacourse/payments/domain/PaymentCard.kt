package woowacourse.payments.domain

data class PaymentCard(
    val cardNumber: CardNumber,
    val expireDate: ExpireDate,
    val ownerName: OwnerName,
    val password: Password,
)
