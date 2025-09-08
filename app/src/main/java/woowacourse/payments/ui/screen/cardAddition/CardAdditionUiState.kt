package woowacourse.payments.ui.screen.cardAddition

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.ExpiredDate

data class CardAdditionUiState(
    val cardNumber: CardNumber,
    val expiredDate: ExpiredDate,
    val ownerName: String,
    val password: CardPassword,
) {
    val isValidCard: Boolean
        get() = cardNumber.isValid && expiredDate.isValid && password.isValid

    val isDateError: Boolean
        get() = expiredDate.value.isNotEmpty() && expiredDate.isValid.not()

    fun update(
        newCardNumber: String? = null,
        newExpiredDate: String? = null,
        newOwnerName: String? = null,
        newPassword: String? = null,
    ): CardAdditionUiState = copy(
        cardNumber = newCardNumber?.let { CardNumber(it) } ?: cardNumber,
        expiredDate = newExpiredDate?.let { ExpiredDate(it) } ?: expiredDate,
        ownerName = newOwnerName ?: ownerName,
        password = newPassword?.let { CardPassword(it) } ?: password,
    )

    companion object {
        val EMPTY_CARD = CardAdditionUiState(
            cardNumber = CardNumber(""),
            expiredDate = ExpiredDate(""),
            ownerName = "",
            password = CardPassword(""),
        )
    }
}
