package woowacourse.payments.ui

import woowacourse.payments.list.CardUiModel

sealed class PaymentCardState {
    data object Empty : PaymentCardState()
    data class CardInfo(val card: CardUiModel) : PaymentCardState()
}