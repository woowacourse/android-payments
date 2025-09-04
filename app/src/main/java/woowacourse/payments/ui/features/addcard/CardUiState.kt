package woowacourse.payments.ui.features.addcard

import woowacourse.payments.domain.Card
import java.time.YearMonth

data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) {
    val isValidCardNumber: Boolean
        get() = cardNumber.length == MAX_LENGTH_CARD_NUMBER && cardNumber.all { it.isDigit() }

    val isValidExpireDate: Boolean
        get() {
            if (expireDate.length != MAX_LENGTH_EXPIRE_DATE) return false
            val mm = expireDate.substring(0, 2).toIntOrNull() ?: return false
            val yy = expireDate.substring(2, 4).toIntOrNull() ?: return false
            if (mm !in 1..12) return false
            val year = 2000 + yy
            val ym = YearMonth.of(year, mm)
            return ym >= YearMonth.now()
        }

    val isValidPassword: Boolean
        get() = password.length == MAX_LENGTH_PASSWORD && password.all { it.isDigit() }

    val isFormValid: Boolean
        get() = isValidCardNumber && isValidExpireDate && isValidPassword

    fun withCardNumber(newNumber: String): CardUiState {
        if (newNumber.length <= MAX_LENGTH_CARD_NUMBER && newNumber.all { it.isDigit() }) {
            return this.copy(cardNumber = newNumber)
        }
        return this
    }

    fun withExpireDate(newDate: String): CardUiState {
        if (newDate.length <= MAX_LENGTH_EXPIRE_DATE && newDate.all { it.isDigit() }) {
            return this.copy(expireDate = newDate)
        }
        return this
    }

    fun withOwnerName(newName: String): CardUiState {
        if (newName.length <= MAX_LENGTH_OWNER_NAME) {
            return this.copy(ownerName = newName)
        }
        return this
    }

    fun withPassword(newPassword: String): CardUiState {
        if (newPassword.length <= MAX_LENGTH_PASSWORD && newPassword.all { it.isDigit() }) {
            return this.copy(password = newPassword)
        }
        return this
    }

    fun toDomainCard(): Card? {
        if (!isFormValid) return null

        val mm = expireDate.substring(0, 2).toInt()
        val yy = expireDate.substring(2, 4).toInt()

        return Card(
            cardNumber = this.cardNumber,
            expireDate = YearMonth.of(2000 + yy, mm),
            ownerName = this.ownerName.ifEmpty { null },
            password = this.password,
        )
    }

    companion object {
        private const val MAX_LENGTH_CARD_NUMBER = 16
        const val MAX_LENGTH_EXPIRE_DATE = 4
        const val MAX_LENGTH_OWNER_NAME = 30
        private const val MAX_LENGTH_PASSWORD = 4
    }
}
