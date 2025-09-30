package woowacourse.payments.domain

import java.lang.Character.isDigit

@JvmInline
value class CardPassword(
    val value: String,
) {
    init {
        require(value.all(::isDigit)) { "카드 비밀번호는 숫자로만 이루어져 있어야 합니다. (입력값: $value)" }
        require(value.length == REQUIRED_LENGTH) { "카드 비밀번호는 ${REQUIRED_LENGTH}자리여야 합니다. (입력된 길이: ${value.length})" }
    }

    companion object {
        private const val REQUIRED_LENGTH: Int = 4
    }
}
