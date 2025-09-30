package woowacourse.payments.domain

data class Password(
    val password: String,
) {
    init {
        require(password.length == PASSWORD_LENGTH) { "비밀번호는 ${PASSWORD_LENGTH}자리여야 합니다." }
        require(password.all { it.isDigit() }) { "비밀번호는 숫자여야 합니다." }
    }

    companion object {
        private const val PASSWORD_LENGTH = 4
    }
}
