package woowacourse.payments.view.cardediting

import woowacourse.payments.view.ui.model.BankTypeUiModel

sealed interface CardEditingUiEvent {
    data object NavigateBack : CardEditingUiEvent

    data object EditCard : CardEditingUiEvent

    data object EditCardSuccess : CardEditingUiEvent

    data object EditCardFailure : CardEditingUiEvent

    data class UpdateCardNumber(
        val cardNumber: String,
    ) : CardEditingUiEvent

    data class UpdateExpiredDate(
        val expiredDate: String,
    ) : CardEditingUiEvent

    data class UpdateHolder(
        val holder: String,
    ) : CardEditingUiEvent

    data class UpdatePassword(
        val password: String,
    ) : CardEditingUiEvent

    data class UpdateBankType(
        val bankType: BankTypeUiModel?,
    ) : CardEditingUiEvent
}
