package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Card

@Parcelize
data class CardUiModel(
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String? = null,
    val password: String = "",
    val cardCompany: CardCompanyUiType,
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

    fun toDomain(): Card =
        Card(
            number = number,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            cardCompany = cardCompany.toDomain(),
        )
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        number = number,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        cardCompany = cardCompany.toUiType(),
    )
