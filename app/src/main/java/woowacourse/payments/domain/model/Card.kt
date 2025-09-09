package woowacourse.payments.domain.model

import woowacourse.payments.ui.model.CardUiModel

data class Card(
    val cardNumber: String,
    val expirationDate: String,
    val userName: String,
    val password: String,
)

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = this.cardNumber,
        expirationDate = this.expirationDate,
        userName = this.userName,
        password = this.password,
    )
