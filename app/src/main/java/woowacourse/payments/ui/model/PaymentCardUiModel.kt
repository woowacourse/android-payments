package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.payments.model.BankUiModel

@Parcelize
data class PaymentCardUiModel(
    val number: String,
    val expirationDate: String,
    val cardholderName: String,
    val bankUiModel: BankUiModel,
) : Parcelable {
    fun formatNumber(
        separator: String = CARD_NUMBER_BASE_SEPARATOR,
        chunkSize: Int = CARD_NUMBER_GROUP_SIZE,
        maskingLowerIndex: Int = CARD_NUMBER_BASE_MASKING_LOWER_INDEX,
        maskingUpperIndex: Int = CARD_NUMBER_BASE_MASKING_UPPER_INDEX,
        mask: String = CARD_NUMBER_BASE_MASK,
    ): String {
        if (number.isBlank()) return ""

        val masked =
            when {
                number.length < maskingLowerIndex -> {
                    number
                }

                number.length in maskingLowerIndex until maskingUpperIndex -> {
                    number.replaceRange(
                        maskingLowerIndex,
                        number.length,
                        mask.repeat(number.length - maskingLowerIndex),
                    )
                }

                else -> {
                    number.replaceRange(
                        maskingLowerIndex,
                        maskingUpperIndex,
                        mask.repeat(maskingUpperIndex - maskingLowerIndex),
                    )
                }
            }

        return masked.chunked(chunkSize).joinToString(separator)
    }

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
