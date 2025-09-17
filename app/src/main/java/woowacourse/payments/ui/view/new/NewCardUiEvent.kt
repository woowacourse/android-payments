package woowacourse.payments.ui.view.new

import woowacourse.payments.domain.CardCompany

sealed interface NewCardUiEvent {
    data class OnChangeBankType(
        val cardCompany: CardCompany,
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
