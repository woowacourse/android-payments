package woowacourse.payments.ui.text

import woowacourse.payments.domain.model.CardNumber

object CardNumberFormatter {
    fun formatAndMask(cardNumber: CardNumber): String {
        val rawValue = cardNumber.value
        val maskedValue = rawValue.replaceRange(8, 16, "********")
        return maskedValue.chunked(CARD_NUMBER_CHUNK_SIZE).joinToString(CARD_NUMBER_SEPARATOR)
    }

    private const val CARD_NUMBER_CHUNK_SIZE = 4
    private const val CARD_NUMBER_SEPARATOR = " - "
}
