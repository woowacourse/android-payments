package woowacourse.payments.util

interface PaymentCardValidator {
    fun validateCardNumber(cardNumber: String): Boolean

    fun validateCardExpirationDate(cardExpirationDate: String): Boolean

    fun validateCardholderName(cardholderName: String): Boolean

    fun validateCardPassword(cardPassword: String): Boolean
}
