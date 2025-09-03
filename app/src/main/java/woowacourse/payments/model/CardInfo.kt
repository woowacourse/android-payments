package woowacourse.payments.model


class CardInfo(
    cardNumber: String = "",
    expireDate: String = "",
    ownerName: String = "",
    password: String = ""
) {
    val cardNumber: String = cardNumber
        .take(CARD_NUMBER_MAX_SIZE)
        .filter { it.isDigit() }
    val expireDate: String = expireDate
        .take(EXPIRE_DATE_MAX_SIZE)
        .filter { it.isDigit() }
    val ownerName: String = ownerName
        .take(OWNER_NAME_MAX_SIZE)
    val password: String = password
        .take(PASSWORD_MAX_SIZE)
        .filter { it.isDigit() }

    fun isExpirationDateValid(): Boolean {
        if (expireDate.length < 2) {
            return true
        }
        return expireDate
            .take(2)
            .toIntOrNull() in 1..12
    }


    companion object {
        const val OWNER_NAME_MAX_SIZE = 30
        private const val CARD_NUMBER_MAX_SIZE = 16
        private const val PASSWORD_MAX_SIZE = 4
        private const val EXPIRE_DATE_MAX_SIZE = 4
    }

}