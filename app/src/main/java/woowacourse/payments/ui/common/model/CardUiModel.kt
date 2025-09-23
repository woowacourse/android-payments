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
        val visibleNumber = number.take(8).chunked(4).joinToString(" - ")
        if (number.isEmpty()) return ""
        return "$visibleNumber - **** - ****"
    }

    private fun formatExpiredDate(date: String): String {
        if (date.isEmpty()) return ""
        return "${date.take(2)} / ${date.takeLast(2)}"
    }
}
