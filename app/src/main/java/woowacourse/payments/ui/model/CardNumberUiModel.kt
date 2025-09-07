package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardNumberUiModel(
    val value: String = "",
) : Parcelable {
    val isValid get() = value.length <= REQUIRE_CARD_NUMBER_LENGTH
    val isError get() = value.length != REQUIRE_CARD_NUMBER_LENGTH

    init {
        require(value.isDigitsOnly()) { ERROR_INVALID_FORMAT }
    }

    companion object {
        private const val REQUIRE_CARD_NUMBER_LENGTH = 16
        private const val ERROR_INVALID_FORMAT = "카드번호는 숫자로만 구성되어야 합니다."
    }
}
