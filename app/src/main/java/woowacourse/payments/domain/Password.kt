package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class Password(
    val value: String
) : Parcelable {
    init {
        require(value.length == 4) { "비밀번호는 4자리입니다" }
        require(value.all(Char::isDigit)) { "비밀 번호에는 숫자만 올 수 있습니다." }
    }
}

