package woowacourse.payments.ui.model

data class CardUiModel(
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpiryDate: String,
) {
    fun maskedCardNumber(): String {
        val visibleText = cardNumber.take(8)
        val maskedText = "*".repeat(8)
        val result = visibleText + maskedText

        return result.chunked(4).joinToString(" - ")
    }

    fun formattedExpiryDate(): String {
        return if (cardExpiryDate.length == 4) {
            cardExpiryDate.substring(0, 2) + " / " + cardExpiryDate.substring(2)
        } else {
            cardExpiryDate
        }
    }
}
