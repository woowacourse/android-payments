package woowacourse.payments.domain.card.exception

sealed class CardNumberException : IllegalArgumentException() {
    data object InvalidLength : CardNumberException()

    data object NotDigit : CardNumberException()
}
