package woowacourse.payments.domain

sealed class CardExpiryValidator {
    data object InvalidFormat : CardExpiryValidator()

    data object InvalidMonth : CardExpiryValidator()

    data object InvalidYear : CardExpiryValidator()

    data object Expired : CardExpiryValidator()
}
