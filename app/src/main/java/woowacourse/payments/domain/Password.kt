package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class Password(
    val value: String
) : Parcelable {
    init {
        require(value.length == PASSWORD_LENGTH) { PasswordException.PasswordLengthException }
        require(value.all(Char::isDigit)) { PasswordException.PasswordTypeException }
    }

    companion object {
        private const val PASSWORD_LENGTH: Int = 4
    }
}


