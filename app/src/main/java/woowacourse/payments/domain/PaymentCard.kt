package woowacourse.payments.domain

data class PaymentCard(
    val cardNumber: String,
    val expiry: String,
    val owner: String,
    val pin: String,
)
