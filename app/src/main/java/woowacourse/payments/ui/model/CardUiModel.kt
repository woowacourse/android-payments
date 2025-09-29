package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val id: Long? = null,
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.NOT_SELECT,
    val cardholderNameUiModel: CardholderNameUiModel = CardholderNameUiModel(),
    val cardNumberUiModel: CardNumberUiModel = CardNumberUiModel(),
    val cardExpirationDateUiModel: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val cardPasswordUiModel: CardPasswordUiModel = CardPasswordUiModel(),
) : Parcelable {
    @IgnoredOnParcel
    val cardholderName: String? get() = cardholderNameUiModel.nameOrNull()

    @IgnoredOnParcel
    val cardCompanyNameResource: Int get() = cardCompanyUiModel.companyName

    @IgnoredOnParcel
    val cardCompanySymbol: Int get() = cardCompanyUiModel.image

    @IgnoredOnParcel
    val cardCompanyColor: Color get() = cardCompanyUiModel.color

    fun formattedCardNumber(
        chunkSize: Int = CARD_NUMBER_CHUNK_SIZE,
        separator: String = CARD_NUMBER_SEPARATOR,
        maskingStartIndex: Int = CARD_NUMBER_MASKING_START_INDEX,
        mask: String = CARD_NUMBER_MASK,
    ): String {
        val maskedNumber =
            buildString {
                cardNumberUiModel.value.mapIndexed { index: Int, number: Char ->
                    if (index >= maskingStartIndex) append(mask) else append(number)
                }
            }

        return maskedNumber.chunked(chunkSize).joinToString(separator)
    }

    fun formattedCardExpirationDate(
        chunkSize: Int = CARD_EXPIRATION_DATE_CHUNK_SIZE,
        separator: String = CARD_EXPIRATION_DATE_SEPARATOR,
    ): String =
        cardExpirationDateUiModel.value
            .chunked(chunkSize)
            .joinToString(separator)

    private companion object {
        private const val CARD_NUMBER_CHUNK_SIZE = 4
        private const val CARD_NUMBER_MASKING_START_INDEX = 8
        private const val CARD_NUMBER_MASK = "*"
        private const val CARD_NUMBER_SEPARATOR = " - "
        private const val CARD_EXPIRATION_DATE_CHUNK_SIZE = 2
        private const val CARD_EXPIRATION_DATE_SEPARATOR = " / "
    }
}
