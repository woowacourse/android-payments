package woowacourse.payments.ui.component

import android.os.Parcelable
import androidx.compose.runtime.Stable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Stable
@Parcelize
data class CardExpirationDateUiModel(
    val cardExpirationDate: String,
) : Parcelable {
    init {
        require(cardExpirationDate.length <= VALID_CARD_EXPIRATION_DATE_LENGTH) { INVALID_EXPIRATION_DATE_LENGTH_MESSAGE }
        require(cardExpirationDate.isDigitsOnly()) { INVALID_EXPIRATION_DATE_MESSAGE }
    }

    @IgnoredOnParcel
    private val yearMonth: YearMonth? =
        runCatching { YearMonth.parse(cardExpirationDate, formatter) }.getOrNull()

    @IgnoredOnParcel
    private val isFilled: Boolean = cardExpirationDate.length == VALID_CARD_EXPIRATION_DATE_LENGTH

    @IgnoredOnParcel
    val isExpired: Boolean = isFilled && yearMonth?.isBefore(YearMonth.now()) ?: false

    @IgnoredOnParcel
    val isInvalidDate = isFilled && yearMonth == null

    @IgnoredOnParcel
    val isValid: Boolean = isExpired.not() && isInvalidDate.not()

    companion object {
        private const val VALID_CARD_EXPIRATION_DATE_LENGTH = 4
        private const val INVALID_EXPIRATION_DATE_LENGTH_MESSAGE =
            "[ERROR] 최대 글자 수는 $VALID_CARD_EXPIRATION_DATE_LENGTH 입니다."
        private const val INVALID_EXPIRATION_DATE_MESSAGE = "[ERROR] 만료일은 숫자만 입력 가능합니다."

        private val formatter = DateTimeFormatter.ofPattern("MMyy")
    }
}
