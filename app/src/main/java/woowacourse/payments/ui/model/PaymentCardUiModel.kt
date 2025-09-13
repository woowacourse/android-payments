package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentCardUiModel(
    val number: String,
    val expirationDate: String,
    val cardholderName: String,
) : Parcelable {
    fun formatNumber(
        separator: String = CARD_NUMBER_BASE_SEPARATOR,
        chunkSize: Int = CARD_NUMBER_GROUP_SIZE,
        maskingLowerIndex: Int = CARD_NUMBER_BASE_MASKING_LOWER_INDEX,
        maskingUpperIndex: Int = CARD_NUMBER_BASE_MASKING_UPPER_INDEX,
        mask: String = CARD_NUMBER_BASE_MASK,
    ): String =
        number
            .replaceRange(
                maskingLowerIndex,
                maskingUpperIndex,
                mask.repeat(maskingUpperIndex - maskingLowerIndex),
            ).chunked(chunkSize)
            .joinToString(separator)

    fun formatExpirationDate(chunkSize: Int = CARD_EXPIRATION_GROUP_SIZE): String =
        expirationDate.chunked(chunkSize).joinToString(CARD_EXPIRATION_BASE_SEPARATOR)

    companion object {
        private const val CARD_NUMBER_GROUP_SIZE = 4
        private const val CARD_NUMBER_BASE_SEPARATOR = "-"
        private const val CARD_NUMBER_BASE_MASKING_LOWER_INDEX = 8
        private const val CARD_NUMBER_BASE_MASKING_UPPER_INDEX = 16
        private const val CARD_NUMBER_BASE_MASK = "*"
        private const val CARD_EXPIRATION_BASE_SEPARATOR = "/"
        private const val CARD_EXPIRATION_GROUP_SIZE = 2
    }
}
