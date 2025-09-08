package woowacourse.payments.domain

data class PaymentCard(
    val cardNumber: String,
    val expirationDate: String,
    val cardOwnerName: String,
    val password: String,
)
