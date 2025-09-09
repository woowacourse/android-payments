package woowacourse.payments.ui.common.model

data class Card(
    val number: String,
    val expiredDate: String,
    val ownerName: String? = null,
    val password: String,
)
