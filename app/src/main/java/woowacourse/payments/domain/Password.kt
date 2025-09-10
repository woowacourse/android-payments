package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.exception.PasswordException

@Parcelize
@JvmInline
value class Password(
    val value: String
) : Parcelable {
    init {
        require(value.length == PASSWORD_LENGTH) { PasswordException.PasswordLengthException.message }
        require(value.all(Char::isDigit)) { PasswordException.PasswordTypeException.message }
    }

    companion object {
        private const val PASSWORD_LENGTH: Int = 4
    }
}


