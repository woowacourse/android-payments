package woowacourse.payments.newcard

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.ExpiredDate

data class NewCardUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
)

fun Card.toUiState(): NewCardUiState =
    NewCardUiState(
        cardNumber = cardNumber.numbers,
        expiredDate = expiredDate.parse(),
        ownerName = ownerName.name,
        password = password.password,
    )

private fun ExpiredDate.parse(): String = "$month$year"
