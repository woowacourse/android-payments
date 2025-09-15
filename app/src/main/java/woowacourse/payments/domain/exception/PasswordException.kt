package woowacourse.payments.domain.exception

sealed class PasswordException(
    override val message: String
): Throwable() {
    data object PasswordLengthException : PasswordException("비밀번호는 4자리입니다")
    data object PasswordTypeException : PasswordException("비밀 번호에는 숫자만 올 수 있습니다.")
}