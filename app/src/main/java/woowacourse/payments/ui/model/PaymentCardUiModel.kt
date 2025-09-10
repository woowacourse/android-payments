package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    private val number: CardNumberUiModel,
    private val expirationDate: CardExpirationDateUiModel,
    private val cardholderName: CardholderNameUiModel,
) : Parcelable {
    fun maskedNumber(mask: String = DEFAULT_MASK): String =
        number.cardNumber
            .chunked(4)
            .mapIndexed { index, chunk ->
                when (index) {
                    0, 1 -> chunk
                    else -> mask
                }
            }.joinToString(DEFAULT_NUMBER_SEPARATOR)

    fun formattedExpirationDate(separator: String = DEFAULT_EXPIRATION_DATE_SEPARATOR): String =
        expirationDate.cardExpirationDate.chunked(2).joinToString(separator)

    val upperCardholderName: String = cardholderName.cardholderName.uppercase()

    companion object {
        private const val DEFAULT_EXPIRATION_DATE_SEPARATOR = " / "
        private const val DEFAULT_NUMBER_SEPARATOR = " - "
        private const val DEFAULT_MASK = "****"
    }
}
