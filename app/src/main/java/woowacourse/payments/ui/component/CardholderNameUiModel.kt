package woowacourse.payments.ui.component

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardholderNameUiModel(
    val cardholderName: String,
    val maxLength: Int = DEFAULT_MAX_LENGTH,
) : Parcelable {
    init {
        require(cardholderName.length <= maxLength) { INVALID_CARDHOLDER_NAME_LENGTH_MESSAGE }
        require(cardholderName.all { it.isLetter() || it.isWhitespace() }) { INVALID_CARDHOLDER_NAME_MESSAGE }
    }

    @IgnoredOnParcel
    val length: Int = cardholderName.length

    companion object {
        private const val DEFAULT_MAX_LENGTH = 30
        private const val INVALID_CARDHOLDER_NAME_LENGTH_MESSAGE =
            "[ERROR] 최대 글자 수는 $DEFAULT_MAX_LENGTH 입니다."
        private const val INVALID_CARDHOLDER_NAME_MESSAGE = "[ERROR] 소유자 이름은 영문만 입력 가능합니다."
    }
}
