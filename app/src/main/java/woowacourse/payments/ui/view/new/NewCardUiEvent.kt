package woowacourse.payments.ui.view.new

import woowacourse.payments.ui.state.CardCompanyState

sealed interface NewCardUiEvent {
    data class OnChangeCardCompany(
        val cardCompany: CardCompanyState = CardCompanyState.Empty,
    ) : NewCardUiEvent

    data class OnChangeCardNumber(
        val cardNumber: String,
    ) : NewCardUiEvent

    data class OnChangeExpireDate(
        val expireDate: String,
    ) : NewCardUiEvent

    data class OnChangeOwnerName(
        val ownerName: String,
    ) : NewCardUiEvent

    data class OnChangePassword(
        val password: String,
    ) : NewCardUiEvent
}
