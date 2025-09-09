package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CardNumber(
    val value: String,
) : Parcelable {
    init {
        require(value.length == 16) { "카드 숫자는 16자리입니다" }
        require(value.all(Char::isDigit)) { "카드 숫자에는 숫자만 올 수 있습니다." }
    }
}
