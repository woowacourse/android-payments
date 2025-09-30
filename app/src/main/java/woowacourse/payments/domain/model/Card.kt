package woowacourse.payments.domain.model

data class Card(
    val id: String,
    val type: CardCompanyType,
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val userName: UserName,
    val password: Password,
)
