package woowacourse.payments.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CardInfo(
    val cardNumber: String,
    val expireDate: LocalDate,
    val ownerName: String,
    val password: String,
) {
    init {
        require(cardNumber.length == CARD_NUMBER_MAX_SIZE) {
            "카드 번호는 $CARD_NUMBER_MAX_SIZE 자리여야 합니다."
        }
        require(ownerName.length <= OWNER_NAME_MAX_SIZE) {
            "카드 소유자 이름은 $OWNER_NAME_MAX_SIZE 자리 이하여야 합니다."
        }
        require(password.length == PASSWORD_MAX_SIZE) {
            "비밀번호는 $PASSWORD_MAX_SIZE 자리여야 합니다."
        }
    }

    companion object {
        fun createOrNull(
            cardNumber: String,
            expireDate: String,
            ownerName: String,
            password: String,
        ): CardInfo? {
            val formattedCardNumber = (cardNumber)
            val formattedExpireDateString = formatExpireDate(expireDate)
            val formattedOwnerName = formatOwnerName(ownerName)
            val formattedPassword = formatPassword(password)
            val parsedExpireDate =
                runCatching {
                    LocalDate.parse(
                        "01$formattedExpireDateString",
                        DateTimeFormatter.ofPattern("ddMMyy"),
                    )
                }.getOrNull()

            if (formattedCardNumber.length != CARD_NUMBER_MAX_SIZE) return null
            if (formattedExpireDateString.length != EXPIRE_DATE_MAX_SIZE) return null
            if (formattedOwnerName.length > OWNER_NAME_MAX_SIZE) return null
            if (formattedPassword.length != PASSWORD_MAX_SIZE) return null
            if (parsedExpireDate == null) return null

            return CardInfo(
                cardNumber = formattedCardNumber,
                expireDate = parsedExpireDate,
                ownerName = formattedOwnerName,
                password = formattedPassword,
            )
        }

        fun checkIsValidMonth(expireDate: String): Boolean =
            expireDate
                .take(2)
                .toIntOrNull() in 1..12

        fun formatCardNumber(cardNumber: String): String =
            cardNumber
                .filter { it.isDigit() }
                .take(CARD_NUMBER_MAX_SIZE)

        fun formatExpireDate(expireDate: String): String =
            expireDate
                .filter { it.isDigit() }
                .take(EXPIRE_DATE_MAX_SIZE)

        fun formatOwnerName(ownerName: String): String =
            ownerName
                .take(OWNER_NAME_MAX_SIZE)

        fun formatPassword(password: String): String =
            password
                .filter { it.isDigit() }
                .take(PASSWORD_MAX_SIZE)

        const val OWNER_NAME_MAX_SIZE = 30
        private const val CARD_NUMBER_MAX_SIZE = 16
        private const val PASSWORD_MAX_SIZE = 4
        private const val EXPIRE_DATE_MAX_SIZE = 4
    }
}
