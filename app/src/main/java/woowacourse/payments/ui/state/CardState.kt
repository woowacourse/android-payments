package woowacourse.payments.ui.state

sealed interface CardState {
    data object Empty : CardState

    data object Pending : CardState

    data class Registered(
        val company: CardCompanyState,
    ) : CardState
}
