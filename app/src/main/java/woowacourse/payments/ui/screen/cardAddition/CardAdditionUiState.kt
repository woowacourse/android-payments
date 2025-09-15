package woowacourse.payments.ui.screen.cardAddition

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.IssuingBank

data class CardAdditionUiState(
    val cardNumber: CardNumber,
    val expiredDate: ExpiredDate,
    val ownerName: String,
    val password: CardPassword,
    val issuingBank: IssuingBank,
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
        issuingBank: IssuingBank? = null,
    ): CardAdditionUiState =
        copy(
            cardNumber = newCardNumber?.let(::CardNumber) ?: cardNumber,
            expiredDate = newExpiredDate?.let(::ExpiredDate) ?: expiredDate,
            ownerName = newOwnerName ?: ownerName,
            password = newPassword?.let(::CardPassword) ?: password,
            issuingBank = issuingBank ?: this.issuingBank,
        )

    companion object {
        val EMPTY_CARD =
            CardAdditionUiState(
                cardNumber = CardNumber(""),
                expiredDate = ExpiredDate(""),
                ownerName = "",
                password = CardPassword(""),
                issuingBank = IssuingBank.NOT_SELECTED,
            )
    }
}

fun CardAdditionUiState.toUiModel(): CardUiModel =
    CardUiModel(
        number = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName,
        issuingBank = issuingBank,
    )
