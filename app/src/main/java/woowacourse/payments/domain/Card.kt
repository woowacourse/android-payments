package woowacourse.payments.domain

data class Card(
    val number: String,
    val expiry: String,
    val password: String,
    val name: String? = null,
)
