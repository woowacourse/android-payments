package woowacourse.payments.domain

data class CardNumber(
    val firstNumber: String,
    val secondNumber: String,
    val thirdNumber: String,
    val fourthNumber: String,
) {
    override fun toString(): String {
        val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)
        return parts.joinToString("")
    }

    fun onValueChange(number: String): CardNumber {
        val pureNumbers = number.chunked(4)
        return copy(
            firstNumber = pureNumbers.getOrNull(0) ?: "",
            secondNumber = pureNumbers.getOrNull(1) ?: "",
            thirdNumber = pureNumbers.getOrNull(2) ?: "",
            fourthNumber = pureNumbers.getOrNull(3) ?: "",
        )
    }
}