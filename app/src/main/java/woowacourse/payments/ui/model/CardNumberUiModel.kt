package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardNumber

@Parcelize
data class CardNumberUiModel(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val thirdNumber: String = "",
    val fourthNumber: String = "",
) : Parcelable {
    @IgnoredOnParcel
    private val parts = listOf(firstNumber, secondNumber, thirdNumber, fourthNumber)

    override fun toString(): String = parts.joinToString("")

    fun toMaskedString(
        mask: String = "*",
        startNumberIndex: Int = 3,
    ): String {
        val maskedParts =
            parts.mapIndexed { index, part ->
                if (index >= (startNumberIndex - 1)) {
                    mask.repeat(part.length)
                } else {
                    part
                }
            }
        return maskedParts.joinToString(" - ")
    }

    fun toDomain() =
        CardNumber(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            thirdNumber = thirdNumber,
            fourthNumber = fourthNumber,
        )
}
