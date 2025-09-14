package woowacourse.payments.domain

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardNumber(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val thirdNumber: String = "",
    val fourthNumber: String = "",
) : Parcelable {
    init {
        val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)
        require(parts.all { it.isDigitsOnly() })
        require(parts.all { it.length <= CARD_NUMBER_PART_LENGTH })
    }

    override fun toString(): String {
        val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)
        return parts.joinToString("")
    }

    fun isValid(): Boolean {
        val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)
        return !parts.any { it.length != CARD_NUMBER_PART_LENGTH }
    }

    fun toFormattedString(): String {
        val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)
        return parts.joinToString(" - ")
    }

    companion object {
        const val CARD_NUMBER_MAX_LENGTH = 16
        const val CARD_NUMBER_PART_LENGTH = 4
        const val FIRST_NUMBER_INDEX = 0
        const val SECOND_NUMBER_INDEX = 1
        const val THIRD_NUMBER_INDEX = 2
        const val FOURTH_NUMBER_INDEX = 3

        fun fromRawInput(number: String): CardNumber {
            val pureNumbers =
                number
                    .filter { it.isDigit() }
                    .take(CARD_NUMBER_MAX_LENGTH)
                    .chunked(CARD_NUMBER_PART_LENGTH)
            return CardNumber(
                firstNumber = pureNumbers.getOrNull(FIRST_NUMBER_INDEX) ?: "",
                secondNumber = pureNumbers.getOrNull(SECOND_NUMBER_INDEX) ?: "",
                thirdNumber = pureNumbers.getOrNull(THIRD_NUMBER_INDEX) ?: "",
                fourthNumber = pureNumbers.getOrNull(FOURTH_NUMBER_INDEX) ?: "",
            )
        }
    }
}
