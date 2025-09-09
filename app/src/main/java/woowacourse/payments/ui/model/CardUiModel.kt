package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class CardUiModel(
    val number: String,
    val expirationDate: String,
    val cardHolderName: String,
) : Parcelable

fun Card.toUiModel(): CardUiModel {
    val maskedNumber = formatUiCardNumber(this.number.number)
    val formattedExpirationDate = formatExpirationDate(this.expirationDate.expirationDate)

    return CardUiModel(
        number = maskedNumber,
        expirationDate = formattedExpirationDate,
        cardHolderName = this.cardHolderName.cardHolderName,
    )
}

private fun formatUiCardNumber(number: String): String {
    val firstTwoGroups = number.substring(0, 8)
    return "$firstTwoGroups-****-****"
}

private fun formatExpirationDate(expirationDate: String): String {
    val month = expirationDate.substring(0, 2)
    val year = expirationDate.substring(2, 4)
    return "$month / $year"
}
