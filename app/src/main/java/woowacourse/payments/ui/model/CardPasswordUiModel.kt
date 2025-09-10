package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardPasswordUiModel(
    val cardPassword: String,
) : Parcelable {
    init {
        require(cardPassword.length <= VALID_CARD_PASSWORD_LENGTH) { INVALID_CARD_PASSWORD_LENGTH_MESSAGE }
        require(cardPassword.isDigitsOnly()) { INVALID_CARD_PASSWORD_MESSAGE }
    }

    @IgnoredOnParcel
    val isFilled: Boolean = cardPassword.length == VALID_CARD_PASSWORD_LENGTH

    companion object {
        private const val VALID_CARD_PASSWORD_LENGTH = 4
        private const val INVALID_CARD_PASSWORD_LENGTH_MESSAGE =
            "[ERROR] 최대 글자 수는 $VALID_CARD_PASSWORD_LENGTH 입니다."
        private const val INVALID_CARD_PASSWORD_MESSAGE = "[ERROR] 비밀번호는 숫자만 입력 가능합니다."
    }
}
