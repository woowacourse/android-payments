package woowacourse.payments.domain.model

data class Card(
    val type: CardCompanyType,
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val userName: UserName,
    val password: Password,
)
