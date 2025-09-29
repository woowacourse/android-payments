package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val id: String = "",
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String? = null,
    val password: String = "",
    val cardCompany: CardCompanyUiType = CardCompanyUiType.NOT_SELECTED,
) : Parcelable {
    val formattedCardNumber: String
        get() = formatCardNumber(number)

    val formattedExpiredDate: String
        get() = formatExpiredDate(expiredDate)

    private fun formatCardNumber(number: String): String {
        if (number.isEmpty()) return ""

        val visiblePart = number.take(8).chunked(4).joinToString(" - ")
        val hiddenPart =
            number
                .drop(8)
                .chunked(4) { "*".repeat(it.length) }
                .joinToString(" - ")

        return listOf(visiblePart, hiddenPart).filter { it.isNotEmpty() }.joinToString(" - ")
    }

    private fun formatExpiredDate(date: String): String {
        if (date.isEmpty()) return ""

        val month = date.take(2)
        val year = date.drop(2)

        return if (year.isEmpty()) month else "$month / $year"
    }
}
