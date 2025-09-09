package woowacourse.payments.ui.cards

import woowacourse.payments.ui.model.PaymentCardUiModel

sealed interface CardScreenState {
    data object None : CardScreenState
    data class Single(val card: PaymentCardUiModel) : CardScreenState
    data class Multiple(val cards: List<PaymentCardUiModel>) : CardScreenState
}