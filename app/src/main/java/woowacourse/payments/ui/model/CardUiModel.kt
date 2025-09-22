package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.color

@Parcelize
data class CardUiModel(
    val number: String,
    val expirationDate: String,
    val cardHolderName: String,
    val bankName: String,
    val bankColor: Long,
) : Parcelable

fun Card.toUiModel(): CardUiModel {
    val maskedNumber = formatUiCardNumber(this.number.number)
    val formattedExpirationDate = formatExpirationDate(this.expirationDate.expirationDate)

    return CardUiModel(
        number = maskedNumber,
        expirationDate = formattedExpirationDate,
        cardHolderName = this.cardHolderName.cardHolderName,
        bankName = this.bank.name,
        bankColor =
            this.bank
                .color()
                .toArgb()
                .toLong(),
    )
}

private fun formatUiCardNumber(number: String): String {
    val firstGroups = number.substring(0, 4)
    val secondGroups = number.substring(4, 8)
    return "$firstGroups - $secondGroups - **** - ****"
}

private fun formatExpirationDate(expirationDate: String): String {
    val month = expirationDate.substring(0, 2)
    val year = expirationDate.substring(2, 4)
    return "$month / $year"
}
