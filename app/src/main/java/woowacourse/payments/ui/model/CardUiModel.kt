package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.lang.Character.isDigit

@Parcelize
data class CardUiModel(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
    val issuingBank: IssuingBank,
) : Parcelable {
    fun formatCardNumber(
        visibleLength: Int = CARD_NUMBER_VISIBLE_LENGTH,
        maskingSign: String = CARD_NUMBER_MASKING_SIGN,
        groupSize: Int = CARD_NUMBER_GROUP_SIZE,
        delimiter: String = CARD_NUMBER_DELIMITER,
    ): String {
        val visiblePart = number.take(visibleLength)
        val maskedPart = maskingSign.repeat(number.length - visibleLength)
        return (visiblePart + maskedPart)
            .chunked(groupSize)
            .joinToString(delimiter)
    }

    fun formatExpiredDate(
        groupSize: Int = CARD_DATE_GROUP_SIZE,
        delimiter: String = CARD_DATE_DELIMITER,
    ): String =
        expiredDate
            .filter(::isDigit)
            .chunked(groupSize)
            .joinToString(delimiter)

    fun formatOwnerName(maxLength: Int = CARD_OWNER_NAME_MAX_LENGTH): String = ownerName.take(maxLength)

    companion object {
        private const val CARD_NUMBER_MASKING_SIGN = "*"
        private const val CARD_NUMBER_DELIMITER = " - "
        private const val CARD_DATE_DELIMITER = " / "
        private const val CARD_NUMBER_VISIBLE_LENGTH = 8
        private const val CARD_NUMBER_GROUP_SIZE = 4
        private const val CARD_DATE_GROUP_SIZE = 2
        private const val CARD_OWNER_NAME_MAX_LENGTH = 15
    }
}
