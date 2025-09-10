package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardNumberUiModel(
    val cardNumber: String,
) : Parcelable {
    init {
        require(cardNumber.length <= VALID_CARD_NUMBER_LENGTH) { INVALID_CARD_NUMBER_LENGTH_MESSAGE }
        require(cardNumber.isDigitsOnly()) { INVALID_CARD_NUMBER_MESSAGE }
    }

    @IgnoredOnParcel
    val isFilled: Boolean = cardNumber.length == VALID_CARD_NUMBER_LENGTH

    companion object {
        private const val VALID_CARD_NUMBER_LENGTH = 16
        private const val INVALID_CARD_NUMBER_LENGTH_MESSAGE =
            "[ERROR] 최대 글자 수는 $VALID_CARD_NUMBER_LENGTH 입니다."
        private const val INVALID_CARD_NUMBER_MESSAGE = "[ERROR] 카드번호는 숫자만 입력 가능합니다."
    }
}
