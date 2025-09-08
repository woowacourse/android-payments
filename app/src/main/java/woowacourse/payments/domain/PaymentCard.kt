package woowacourse.payments.domain

data class PaymentCard(
    val number: CardNumber,
    val expirationDate: CardExpirationDate,
    val password: CardPassword,
    val holderName: CardHolderName? = null,
)
