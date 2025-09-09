package woowacourse.payments.domain

import java.time.YearMonth

class DefaultPaymentCardValidator : PaymentCardValidator {

    override fun validateCardNumber(cardNumber: String): Boolean =
        cardNumber.length == InputType.CardNumber.maxLength

    override fun validateCardExpirationDate(cardExpirationDate: String): Boolean =
        cardExpirationDate.length == InputType.ExpiryDate.maxLength &&
                isValidCardExpirationDate(cardExpirationDate)

    override fun validateCardholderName(cardholderName: String): Boolean =
        cardholderName.isNotBlank() && cardholderName.length <= InputType.CardholderName.maxLength

    override fun validateCardPassword(cardPassword: String): Boolean =
        cardPassword.length == InputType.Password.maxLength

    private fun isValidCardExpirationDate(cardExpirationDate: String): Boolean {
        val month = cardExpirationDate.substring(0, 2).toIntOrNull() ?: return false
        val year = cardExpirationDate.substring(2, 4).toIntOrNull() ?: return false

        val inputYearMonth =
            runCatching { YearMonth.of(CENTURY_OFFSET + year, month) }.getOrNull() ?: return false
        val currentYearMonth = YearMonth.now()

        return !inputYearMonth.isBefore(currentYearMonth)
    }

    companion object {
        private const val CENTURY_OFFSET = 2000
    }
}
