package woowacourse.payments.domain.exception

sealed class CardNumberException(
    override val message: String
) : Throwable() {
    data object CardNumberLengthException : CardNumberException("카드 숫자는 16자리입니다")
    data object CardNumberTypeException : CardNumberException("카드 숫자에는 숫자만 올 수 있습니다.")
}
