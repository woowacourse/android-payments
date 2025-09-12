package woowacourse.payments.ui.text

import woowacourse.payments.domain.model.CardNumber

object CardNumberFormatter {
    fun formatAndMask(cardNumber: CardNumber): String {
        val rawValue = cardNumber.value

        val maskLength = END_MASK_INDEX - START_MASK_INDEX
        val dynamicMask = MASK_CHARACTER.repeat(maskLength)

        val maskedValue =
            rawValue.replaceRange(
                START_MASK_INDEX,
                END_MASK_INDEX,
                dynamicMask,
            )
        return maskedValue.chunked(CARD_NUMBER_CHUNK_SIZE).joinToString(CARD_NUMBER_SEPARATOR)
    }

    private const val CARD_NUMBER_CHUNK_SIZE = 4
    private const val CARD_NUMBER_SEPARATOR = " - "

    private const val START_MASK_INDEX = 8
    private const val END_MASK_INDEX = 16
    private const val MASK_CHARACTER = "*"
}
