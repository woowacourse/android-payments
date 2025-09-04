package woowacourse.payments.domain

sealed class ExpiryException : Throwable() {
    data object Valid : ExpiryException()

    data object InvalidFormat : ExpiryException()

    data object InvalidMonth : ExpiryException()

    data object InvalidYear : ExpiryException()

    data object Expired : ExpiryException()
}
