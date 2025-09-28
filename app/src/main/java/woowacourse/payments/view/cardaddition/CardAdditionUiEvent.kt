package woowacourse.payments.view.cardaddition

import woowacourse.payments.view.ui.model.BankTypeUiModel

sealed interface CardAdditionUiEvent {
    data object NavigateBack : CardAdditionUiEvent

    data object AddCard : CardAdditionUiEvent

    data object AddCardSuccess : CardAdditionUiEvent

    data object AddCardFailure : CardAdditionUiEvent

    data class UpdateCardNumber(
        val cardNumber: String,
    ) : CardAdditionUiEvent

    data class UpdateExpiredDate(
        val expiredDate: String,
    ) : CardAdditionUiEvent

    data class UpdateHolder(
        val holder: String,
    ) : CardAdditionUiEvent

    data class UpdatePassword(
        val password: String,
    ) : CardAdditionUiEvent

    data class UpdateBankType(
        val bankType: BankTypeUiModel?,
    ) : CardAdditionUiEvent
}
