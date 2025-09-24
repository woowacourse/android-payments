package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardNumberUiModel(
    val value: String = "",
    val errorMessage: String? = null,
) : Parcelable {
    init {
        require(value.isDigitsOnly())
        require(value.length <= REQUIRE_CARD_NUMBER_LENGTH)
    }

    fun isValid(): Boolean = value.length == REQUIRE_CARD_NUMBER_LENGTH

    companion object {
        private const val REQUIRE_CARD_NUMBER_LENGTH = 16
    }
}
