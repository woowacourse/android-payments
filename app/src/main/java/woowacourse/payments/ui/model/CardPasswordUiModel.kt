package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
class CardPasswordUiModel(
    val value: String = "",
) : Parcelable {
    init {
        require(value.isDigitsOnly())
        require(value.length <= REQUIRE_CARD_PASSWORD_LENGTH)
    }

    fun isValid(): Boolean = value.length == REQUIRE_CARD_PASSWORD_LENGTH

    companion object {
        private const val REQUIRE_CARD_PASSWORD_LENGTH = 4
    }
}
