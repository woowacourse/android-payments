package woowacourse.payments.ui.model

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.format.ExpirationDateFormat

data class CardUiModel(
    val cardNumber: String,
    val expirationDate: String,
    val cardholderName: String,
    val passcode: String,
) {
    companion object {
        val EMPTY = CardUiModel("", "", "", "")
    }
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormat.formatted(cardNumber),
        expirationDate = ExpirationDateFormat.formatted(expirationDate),
        cardholderName = cardholderName.value,
        passcode = passcode.value,
    )
