package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val bankType: BankTypeUiModel,
    val number: CardNumberUiModel,
    val expirationDate: CardExpirationDateUiModel,
    val cardholderName: CardholderNameUiModel,
) : Parcelable {
    fun displayCardNumber(
        mask: String = DEFAULT_MASK,
        maskingRange: IntRange = DEFAULT_MASKING_RANGE,
    ): String {
        val safeRange = maskingRange.coerceInLength(number.number.length)
        return number.number
            .replaceRange(safeRange, mask.repeat(safeRange.count()))
            .chunked(4)
            .joinToString(DEFAULT_NUMBER_SEPARATOR)
    }

    fun displayExpirationDate(separator: String = DEFAULT_EXPIRATION_DATE_SEPARATOR): String =
        expirationDate.expirationDate.chunked(2).joinToString(separator)

    @IgnoredOnParcel
    val upperCardholderName: String = cardholderName.displayedName.uppercase()

    private fun IntRange.coerceInLength(length: Int): IntRange = (first.coerceAtMost(length)..last.coerceAtMost(length - 1))

    companion object {
        private const val DEFAULT_EXPIRATION_DATE_SEPARATOR = " / "
        private const val DEFAULT_NUMBER_SEPARATOR = " - "
        private const val DEFAULT_MASK = "*"
        private val DEFAULT_MASKING_RANGE = 8..15
    }
}
