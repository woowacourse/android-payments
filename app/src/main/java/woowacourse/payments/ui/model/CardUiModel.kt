package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpiryDate: String,
    val cardPassword: String,
    val cardCompanyUiModel: CardCompanyUiModel?,
) : Parcelable {
    fun maskedCardNumber(): String {
        val visibleText = cardNumber.take(8)
        val maskedText = "*".repeat(8)
        val result = visibleText + maskedText

        return result.chunked(4).joinToString(" - ")
    }

    fun formattedExpiryDate(): String =
        if (cardExpiryDate.length == 4) {
            "${cardExpiryDate.substring(0, 2)} / ${cardExpiryDate.substring(2, 4)}"
        } else {
            cardExpiryDate
        }

    fun isDifferentFrom(
        number: String,
        expiry: String,
        holder: String,
        password: String,
        company: CardCompanyUiModel?,
    ): Boolean =
        cardNumber != number ||
            cardExpiryDate != expiry ||
            cardHolderName != holder ||
            cardPassword != password ||
            cardCompanyUiModel != company
}
