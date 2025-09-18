package woowacourse.payments.ui.state

import woowacourse.payments.domain.Card

sealed interface CardState {
    data object Empty : CardState

    data object Pending : CardState

    data class Registered(
        val card: Card,
    ) : CardState
}
