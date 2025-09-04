package woowacourse.payments.util

import java.time.YearMonth

class DefaultPaymentCardValidator : PaymentCardValidator {
    override fun validateCardNumber(cardNumber: String): Boolean = cardNumber.length == VALID_CARD_NUMBER_LENGTH

    override fun validateCardExpirationDate(cardExpirationDate: String): Boolean =
        cardExpirationDate.length == VALID_CARD_EXPIRATION_DATE_LENGTH &&
            isValidCardExpirationDate(cardExpirationDate)

    override fun validateCardholderName(cardholderName: String): Boolean = true

    override fun validateCardPassword(cardPassword: String): Boolean = cardPassword.length == VALID_CARD_PASSWORD_LENGTH

    private fun isValidCardExpirationDate(cardExpirationDate: String): Boolean {
        val month = cardExpirationDate.substring(0, 2).toIntOrNull() ?: return false
        val year = cardExpirationDate.substring(2, 4).toIntOrNull() ?: return false

        val inputYearMonth =
            runCatching { YearMonth.of(CENTURY_OFFSET + year, month) }.getOrNull() ?: return false
        val currentYearMonth = YearMonth.now()

        return !inputYearMonth.isBefore(currentYearMonth)
    }

    companion object {
        private const val VALID_CARD_NUMBER_LENGTH = 16
        private const val VALID_CARD_EXPIRATION_DATE_LENGTH = 4
        private const val VALID_CARD_PASSWORD_LENGTH = 4
        private const val CENTURY_OFFSET = 2_000
    }
}
