package woowacourse.payments.domain

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
) {
    companion object {
        val EMPTY = Card("", "", "", "")
        const val CARD_MAX_LENGTH = 16
        const val CARD_NUMBER_MASKING_LENGTH = 8
    }
}
