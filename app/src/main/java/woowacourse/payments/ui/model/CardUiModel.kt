package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.format.ExpirationDateFormat

@Parcelize
data class CardUiModel(
    val cardNumber: String,
    val expirationDate: String,
    val cardholderName: String,
    val passcode: String,
) : Parcelable {
    companion object {
        val EMPTY = CardUiModel("", "", "", "")
    }
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormat.formatted(cardNumber),
        expirationDate = ExpirationDateFormat.formatted(expirationDate),
        cardholderName = cardholderName.value,
        passcode = passcode.value,
    )
