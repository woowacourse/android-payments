package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class Password(
    val value: String,
) : Parcelable {
    init {
        require(checkValidPassword(value)) {
            "비밀번호가 유효하지 않습니다."
        }
    }

    private fun checkValidPassword(password: String): Boolean = password.length == MAX_LENGTH_PASSWORD && password.all(Char::isDigit)

    companion object {
        const val MAX_LENGTH_PASSWORD = 4
    }
}
