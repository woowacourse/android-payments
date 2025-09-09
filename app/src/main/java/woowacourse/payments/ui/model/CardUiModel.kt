package woowacourse.payments.ui.model

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.formatter.CardNumberFormat
import woowacourse.payments.ui.formatter.ExpirationDateFormat

data class CardUiModel(
    val number: String = "",
    val expirationDate: String = "",
    val holderName: String = "",
) {
    companion object {
        fun from(card: Card): CardUiModel =
            CardUiModel(
                number = CardNumberFormat.formattedCardNumber(card.number.value),
                expirationDate = ExpirationDateFormat.formattedExpirationDate(card.expirationDate.value),
                holderName = card.holderName.value,
            )
    }
}
