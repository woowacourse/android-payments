package woowacourse.payments.domain.exception

sealed class PasswordException : IllegalArgumentException() {
    data object InvalidLength : PasswordException()

    data object NotDigit : PasswordException()
}
