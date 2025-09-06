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
    fun withCardNumber(newNumber: String): CardUiState =
        this.copy(cardNumber = newNumber.filter { it in '0'..'9' }.take(MAX_LENGTH_CARD_NUMBER))

    fun withExpireDate(newDate: String): CardUiState =
        this.copy(expireDate = newDate.filter { it in '0'..'9' }.take(MAX_LENGTH_EXPIRE_DATE))

    fun withOwnerName(newName: String): CardUiState = this.copy(ownerName = newName.take(MAX_LENGTH_OWNER_NAME))

    fun withPassword(newPassword: String): CardUiState = this.copy(newPassword.filter { it in '0'..'9' }.take(MAX_LENGTH_PASSWORD))
}
