package woowacourse.payments.domain

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
) {
    fun formatCardNumber(): String {
        val visibleLength = CARD_NUMBER_MASKING_LENGTH
        val visiblePart = number.take(visibleLength)
        val maskedPart = CARD_MASKING_CHAR.repeat(number.length - visibleLength)
        return (visiblePart + maskedPart)
            .chunked(CARD_NUMBER_GROUP_SIZE)
            .joinToString(CARD_SEPARATOR)
    }

    fun formatExpireDate(): String = expireDate.chunked(CARD_EXPIRE_DATE_GROUP_SIZE)
        .joinToString(CARD_EXPIRE_DATE_SEPARATOR)

    companion object {
        val EMPTY = Card("", "", "", "")
        const val CARD_NUMBER_GROUP_SIZE = 4
        const val CARD_SEPARATOR = " - "
        const val CARD_MAX_LENGTH = 16
        private const val CARD_MASKING_CHAR = "*"
        private const val CARD_NUMBER_MASKING_LENGTH = 8

        const val CARD_EXPIRE_DATE_GROUP_SIZE = 2
        const val CARD_EXPIRE_DATE_SEPARATOR = " / "
    }
}
