package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardHolderUiModel(
    val value: String,
) : Parcelable {
    init {
        require(
            value.length in CARD_HOLDER_MIN_LENGTH..CARD_HOLDER_MAX_LENGTH,
        ) { throw IllegalArgumentException(MESSAGE_INVALID_NAME_LENGTH) }
    }

    companion object {
        private const val MESSAGE_INVALID_NAME_LENGTH: String = "INVALID_NAME_LENGTH"
        private const val CARD_HOLDER_MIN_LENGTH: Int = 1
        const val CARD_HOLDER_MAX_LENGTH: Int = 30
    }
}
