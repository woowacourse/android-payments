package woowacourse.payments.domain

interface PaymentCardValidator {
    fun validateCardNumber(cardNumber: String): PaymentCardValidationResult

    fun validateCardExpirationDate(cardExpirationDate: String): PaymentCardValidationResult

    fun validateCardholderName(cardholderName: String): PaymentCardValidationResult

    fun validateCardPassword(cardPassword: String): PaymentCardValidationResult

    enum class PaymentCardValidationResult {
        NOT_FILLED,
        INVALID,
        VALID,
    }
}
