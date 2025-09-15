package woowacourse.payments.domain

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize

@Parcelize
data class Password(
    val password: String = "",
) : Parcelable {
    init {
        require(password.isDigitsOnly())
        require(password.length <= CARD_PASSWORD_MAX_LENGTH)
    }

    override fun toString(): String = password

    fun isValid(): Boolean = password.length == CARD_PASSWORD_MAX_LENGTH

    companion object {
        const val CARD_PASSWORD_MAX_LENGTH = 4

        fun fromRawInput(value: String): Password {
            val newPassword = value.filter { it.isDigit() }.take(CARD_PASSWORD_MAX_LENGTH)
            return Password(newPassword)
        }
    }
}
