package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.extension.coerceInLength

@Parcelize
data class PaymentCardUiModel(
    private val number: CardNumberUiModel,
    private val expirationDate: CardExpirationDateUiModel,
    private val cardholderName: CardholderNameUiModel,
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

    companion object {
        private const val DEFAULT_EXPIRATION_DATE_SEPARATOR = " / "
        private const val DEFAULT_NUMBER_SEPARATOR = " - "
        private const val DEFAULT_MASK = "*"
        private val DEFAULT_MASKING_RANGE = 8..15
    }
}
