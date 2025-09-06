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
        const val CARD_MASKING_CHAR = "*"
        const val MASKING_LENGTH = 8
    }
}
