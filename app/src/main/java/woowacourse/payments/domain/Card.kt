package woowacourse.payments.domain

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
)
