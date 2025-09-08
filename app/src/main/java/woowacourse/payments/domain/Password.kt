package woowacourse.payments.domain

@JvmInline
value class Password(
    val password: String,
) {
    init {
        require(password.length == 4 && password.all { it.isDigit() }) {
            "비밀번호는 4자리 숫자여야 합니다."
        }
    }
}
