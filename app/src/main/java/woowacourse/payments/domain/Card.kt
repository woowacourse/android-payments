package woowacourse.payments.domain

data class Card(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) {
    fun withCardNumber(newNumber: String): Card {
        if (newNumber.length <= MAX_LENGTH_CARD_NUMBER && newNumber.all { it.isDigit() }) {
            return this.copy(cardNumber = newNumber)
        }
        return this
    }

    fun withExpireDate(newDate: String): Card {
        if (newDate.length <= MAX_LENGTH_EXPIRE_DATE && newDate.all { it.isDigit() }) {
            return this.copy(expireDate = newDate)
        }
        return this
    }

    fun withOwnerName(newName: String): Card {
        if (newName.length <= MAX_LENGTH_OWNER_NAME) {
            return this.copy(ownerName = newName)
        }
        return this
    }

    fun withPassword(newPassword: String): Card {
        if (newPassword.length <= MAX_LENGTH_PASSWORD && newPassword.all { it.isDigit() }) {
            return this.copy(password = newPassword)
        }
        return this
    }

    companion object {
        private const val MAX_LENGTH_CARD_NUMBER = 16
        private const val MAX_LENGTH_EXPIRE_DATE = 4
        const val MAX_LENGTH_OWNER_NAME = 30
        private const val MAX_LENGTH_PASSWORD = 4
    }
}
