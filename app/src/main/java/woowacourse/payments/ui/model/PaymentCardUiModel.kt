package woowacourse.payments.ui.model

data class PaymentCardUiModel(
    val cardNumbers: String,
    val cardExpiry: String,
    val ownerName: String,
    val password: String
) {
    fun maskCardNumbersFromBack(
        visibleGroups: Int = 2,
        groupSize: Int = 4,
        separator: String = " - ",
        maskingChar: Char = '*'
    ): String {
        val groups = cardNumbers.chunked(groupSize)
        val firstVisible = groups.take(visibleGroups)
        val masked = groups.drop(visibleGroups).map { maskingChar.toString().repeat(groupSize) }
        return (firstVisible + masked).joinToString(separator)
    }


    fun formatCardExpiry(
        separator: String = " / ",
    ): String =
        cardExpiry.chunked(2)
            .joinToString(separator)
}

val paymentCardUiModelSample =
    PaymentCardUiModel("1234567812345678", "1225", "빰".repeat(30), "1234")