package woowacourse.payments.domain

sealed class CardExpiryException : Throwable() {
    data object InvalidFormat : CardExpiryException()

    data object InvalidMonth : CardExpiryException()

    data object InvalidYear : CardExpiryException()

    data object Expired : CardExpiryException()
}
