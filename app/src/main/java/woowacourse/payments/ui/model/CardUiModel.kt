package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.color

@Parcelize
data class CardUiModel(
    val id: Long,
    val number: String,
    val maskedNumber: String,
    val expirationDate: String,
    val formattedExpirationDate: String,
    val cardHolderName: String,
    val password: String,
    val bankName: String,
    val bankColor: Long,
) : Parcelable

fun Card.toUiModel(): CardUiModel {
    val maskedNumber = formatUiCardNumber(this.cardNumber.number)
    val formattedExpirationDate = formatExpirationDate(this.expirationDate.expirationDate)

    return CardUiModel(
        id = this.id,
        number = this.cardNumber.number,
        maskedNumber = maskedNumber,
        expirationDate = this.expirationDate.expirationDate,
        formattedExpirationDate = formattedExpirationDate,
        cardHolderName = this.cardHolderName.cardHolderName,
        password = this.password.password,
        bankName = this.bank.name,
        bankColor =
            this.bank
                .color()
                .toArgb()
                .toLong(),
    )
}

fun formatUiCardNumber(number: String): String =
    if (number.length >= 8) {
        val firstGroups = number.substring(0, 4)
        val secondGroups = number.substring(4, 8)
        "$firstGroups - $secondGroups - **** - ****"
    } else {
        number
    }

fun formatExpirationDate(expirationDate: String): String =
    if (expirationDate.length >= 4) {
        val month = expirationDate.substring(0, 2)
        val year = expirationDate.substring(2, 4)
        "$month / $year"
    } else {
        expirationDate
    }
