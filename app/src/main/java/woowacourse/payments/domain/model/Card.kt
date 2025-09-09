package woowacourse.payments.domain.model

data class Card(
    val cardNumber: String,
    val expirationDate: String,
    val userName: String,
    val password: String,
)
