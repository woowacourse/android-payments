package woowacourse.payments.domain.exception

sealed class CardNumberException : IllegalArgumentException() {
    data object InvalidLength : CardNumberException()

    data object NotDigit : CardNumberException()
}
