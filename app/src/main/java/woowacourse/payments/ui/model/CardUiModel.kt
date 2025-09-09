package woowacourse.payments.ui.model

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.formatter.CardNumberFormat
import woowacourse.payments.ui.formatter.ExpirationDateFormat

data class CardUiModel(
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cardholderName: String = "",
) {
    companion object {
        fun from(card: Card): CardUiModel =
            CardUiModel(
                cardNumber = CardNumberFormat.formattedCardNumber(card.cardNumber.value),
                expirationDate = ExpirationDateFormat.formattedExpirationDate(card.expirationDate.value),
                cardholderName = card.cardholderName.value,
            )
    }
}
