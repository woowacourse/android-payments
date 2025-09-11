package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    private val cardholderNameUiModel: CardholderNameUiModel = CardholderNameUiModel(),
    private val cardNumberUiModel: CardNumberUiModel = CardNumberUiModel(),
    private val cardExpirationDateUiModel: CardExpirationDateUiModel = CardExpirationDateUiModel(),
) : Parcelable {
    @IgnoredOnParcel
    val cardholderName: String? = cardholderNameUiModel.nameOrNull()

    fun formattedCardNumber(
        chunkSize: Int = CARD_NUMBER_CHUNK_SIZE,
        separator: String = CARD_NUMBER_SEPARATOR,
        maskingLowerIndex: Int = CARD_NUMBER_MASKING_LOWER_INDEX,
        maskingUpperIndex: Int = CARD_NUMBER_MASKING_UPPER_INDEX,
        mask: String = CARD_NUMBER_MASK,
    ): String =
        cardNumberUiModel.value
            .replaceRange(
                maskingLowerIndex,
                maskingUpperIndex,
                mask.repeat(maskingUpperIndex - maskingLowerIndex),
            ).chunked(chunkSize)
            .joinToString(separator)

    fun formattedCardExpirationDate(
        chunkSize: Int = CARD_EXPIRATION_DATE_CHUNK_SIZE,
        separator: String = CARD_EXPIRATION_DATE_SEPARATOR,
    ): String =
        cardExpirationDateUiModel.value
            .chunked(chunkSize)
            .joinToString(separator)

    private companion object {
        private const val CARD_NUMBER_CHUNK_SIZE = 4
        private const val CARD_NUMBER_MASKING_LOWER_INDEX = 8
        private const val CARD_NUMBER_MASKING_UPPER_INDEX = 16
        private const val CARD_NUMBER_MASK = "*"
        private const val CARD_NUMBER_SEPARATOR = " - "
        private const val CARD_EXPIRATION_DATE_CHUNK_SIZE = 2
        private const val CARD_EXPIRATION_DATE_SEPARATOR = " / "
    }
}
