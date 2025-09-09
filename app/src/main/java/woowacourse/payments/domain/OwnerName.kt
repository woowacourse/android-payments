package woowacourse.payments.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class OwnerName(
    val value: String,
) : Parcelable {
    init {
        require(value.length in 1..30) { "소유자의 이름의 길이는 1 ~ 30자 사이이다." }
        requireNotNull(value.all { it.isWhitespace() }) { "소유자의 이름은 공백으로만 이루어질 수 없습니다." }
        require(value.all { it in 'a'..'z' || it in 'A'..'Z' || it.isWhitespace() }) { "소유자의 이름에는 문자와 공백 가능하다" }
    }
}

