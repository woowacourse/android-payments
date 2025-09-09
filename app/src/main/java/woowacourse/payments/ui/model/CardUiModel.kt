package woowacourse.payments.ui.model

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.formatter.CardNumberFormat
import woowacourse.payments.ui.formatter.ExpirationDateFormat

data class CardUiModel(
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cardholderName: String = "",
)

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormat.formattedCardNumber(cardNumber.value),
        expirationDate = ExpirationDateFormat.formattedExpirationDate(expirationDate.value),
        cardholderName = cardholderName.value,
    )
