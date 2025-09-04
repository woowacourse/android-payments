package woowacourse.payments.ui.features.addcard

import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_CARD_NUMBER
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_EXPIRE_DATE
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_OWNER_NAME
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_PASSWORD

data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) {
    fun withCardNumber(newNumber: String): CardUiState {
        if (newNumber.length <= MAX_LENGTH_CARD_NUMBER && newNumber.all(Char::isDigit)) {
            return this.copy(cardNumber = newNumber)
        }
        return this
    }

    fun withExpireDate(newDate: String): CardUiState {
        if (newDate.length <= MAX_LENGTH_EXPIRE_DATE && newDate.all(Char::isDigit)) {
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
        if (newPassword.length <= MAX_LENGTH_PASSWORD && newPassword.all(Char::isDigit)) {
            return this.copy(password = newPassword)
        }
        return this
    }
}
