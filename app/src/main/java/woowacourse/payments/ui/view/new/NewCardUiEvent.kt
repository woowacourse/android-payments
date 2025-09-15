package woowacourse.payments.ui.view.new

import woowacourse.payments.domain.Banks

sealed interface NewCardUiEvent {
    data class OnChangeBankType(
        val banks: Banks,
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
