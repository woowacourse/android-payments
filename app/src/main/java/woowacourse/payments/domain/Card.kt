package woowacourse.payments.domain

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
) {
    fun formatCardNumber(
        groupSize: Int,
        separator: String,
        cardMaskChar: String,
    ): String {
        val visibleLength = CARD_NUMBER_MASKING_LENGTH
        val visiblePart = number.take(visibleLength)
        val maskedPart = cardMaskChar.repeat(number.length - visibleLength)
        return (visiblePart + maskedPart)
            .chunked(groupSize)
            .joinToString(separator)
    }

    fun formatExpireDate(
        groupSize: Int,
        separator: String
    ): String =
        expireDate.chunked(groupSize)
            .joinToString(separator)

    companion object {
        val EMPTY = Card("", "", "", "")
        const val CARD_MAX_LENGTH = 16
        private const val CARD_NUMBER_MASKING_LENGTH = 8
    }
}
