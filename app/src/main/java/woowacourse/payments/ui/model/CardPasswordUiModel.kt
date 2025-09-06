package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
class CardPasswordUiModel(
    val value: String = "",
) : Parcelable {
    val isValid: Boolean get() = value.length <= VALID_LENGTH
    val isError: Boolean get() = value.length != VALID_LENGTH

    init {
        require(value.isDigitsOnly()) { ERROR_INVALID_FORMAT }
    }

    companion object {
        private const val ERROR_INVALID_FORMAT = "카드 비밀번호는 숫자로만 구성되어야 합니다."
        private const val VALID_LENGTH = 4
    }
}
