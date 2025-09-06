package woowacourse.payments.domain

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
) {
    companion object {
        const val CARD_NUMBER_GROUP_SIZE = 4
        const val CARD_SEPARATOR = " - "
        const val CARD_MAX_LENGTH = 16
        private const val CARD_MASKING_CHAR = "*"
        private const val CARD_NUMBER_MASKING_LENGTH = 8

        const val CARD_EXPIRE_DATE_GROUP_SIZE = 4
        const val CARD_EXPIRE_DATE_SEPARATOR = " / "
    }
}
