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
        val pureNumbers = number.chunked(CARD_NUMBER_PART_LENGTH)
        return copy(
            firstNumber = pureNumbers.getOrNull(FIRST_NUMBER_INDEX) ?: "",
            secondNumber = pureNumbers.getOrNull(SECOND_NUMBER_INDEX) ?: "",
            thirdNumber = pureNumbers.getOrNull(THIRD_NUMBER_INDEX) ?: "",
            fourthNumber = pureNumbers.getOrNull(FORTH_NUMBER_INDEX) ?: "",
        )
    }

    companion object {
        const val CARD_NUMBER_PART_LENGTH = 4
        const val FIRST_NUMBER_INDEX = 0
        const val SECOND_NUMBER_INDEX = 1
        const val THIRD_NUMBER_INDEX = 2
        const val FORTH_NUMBER_INDEX = 3
    }
}
