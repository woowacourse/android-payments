package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardHolderUiModel(
    val value: String,
) : Parcelable {
    init {
        require(value.length < CARD_HOLDER_MAX_LENGTH) { MESSAGE_INVALID_NAME_LENGTH }
    }

    companion object {
        private const val MESSAGE_INVALID_NAME_LENGTH: String = "INVALID_NAME_LENGTH"
        const val CARD_HOLDER_MAX_LENGTH: Int = 30
    }
}
