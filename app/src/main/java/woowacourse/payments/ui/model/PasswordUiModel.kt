package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PasswordUiModel(
    val value: String,
) : Parcelable {
    init {
        require(value.length == PASSWORD_LENGTH) { throw IllegalArgumentException(MESSAGE_INVALID_PASSWORD_LENGTH) }
    }

    companion object {
        private const val MESSAGE_INVALID_PASSWORD_LENGTH: String = "INVALID_PASSWORD_LENGTH"
        const val PASSWORD_LENGTH: Int = 4
    }
}
