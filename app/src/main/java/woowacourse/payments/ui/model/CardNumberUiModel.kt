package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardNumberUiModel(
    val value: String,
) : Parcelable {
    init {
        require(value.length == CARD_NUMBER_LENGTH) { MESSAGE_INVALID_CARD_NUMBER_LENGTH }
    }

    companion object {
        private const val MESSAGE_INVALID_CARD_NUMBER_LENGTH: String = "INVALID_CARD_NUMBER_LENGTH"
        const val CARD_NUMBER_LENGTH: Int = 16
    }
}
