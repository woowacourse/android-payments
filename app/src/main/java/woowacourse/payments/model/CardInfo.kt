package woowacourse.payments.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CardInfo(
    val cardNumber: String,
    val expireDate: LocalDate,
    val ownerName: String,
    val password: String
) {
    companion object {
        fun isExpirationDateValid(expireDate: String): Boolean {
            if (expireDate.length < 2) {
                return true
            }
            return expireDate
                .take(2)
                .toIntOrNull() in 1..12
        }

        fun formatCardNumber(cardNumber: String): String = cardNumber
                .filter { it.isDigit() }
                .take(CARD_NUMBER_MAX_SIZE)

        fun formatExpireDate(expireDate: String): String = expireDate
                .filter { it.isDigit() }
                .take(EXPIRE_DATE_MAX_SIZE)

        fun formatOwnerName(ownerName: String): String = ownerName
                .take(OWNER_NAME_MAX_SIZE)

        fun formatPassword(password: String): String = password
                .filter { it.isDigit() }
                .take(PASSWORD_MAX_SIZE)

        const val OWNER_NAME_MAX_SIZE = 30
        private const val CARD_NUMBER_MAX_SIZE = 16
        private const val PASSWORD_MAX_SIZE = 4
        private const val EXPIRE_DATE_MAX_SIZE = 4
    }

}