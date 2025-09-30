package woowacourse.payments.domain

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PasswordUiModel

@Parcelize
data class Password(
    val password: String = "",
) : Parcelable {
    init {
        require(password.isDigitsOnly())
        require(password.length <= CARD_PASSWORD_MAX_LENGTH)
    }

    fun isValid(): Boolean = password.length == CARD_PASSWORD_MAX_LENGTH

    fun toUiModel(): PasswordUiModel =
        PasswordUiModel(
            password = password,
        )

    companion object {
        const val CARD_PASSWORD_MAX_LENGTH = 4

        fun fromRawInput(value: String): Password {
            val newPassword = value.filter { it.isDigit() }.take(CARD_PASSWORD_MAX_LENGTH)
            return Password(newPassword)
        }
    }
}
