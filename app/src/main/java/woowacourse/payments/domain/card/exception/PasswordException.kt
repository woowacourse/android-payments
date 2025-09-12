package woowacourse.payments.domain.card.exception

sealed class PasswordException : IllegalArgumentException() {
    data object InvalidLength : PasswordException()

    data object NotDigit : PasswordException()
}
