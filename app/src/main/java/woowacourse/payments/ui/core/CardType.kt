package woowacourse.payments.ui.core

sealed interface CardType {
    data object Empty : CardType

    data object Pending : CardType

    data class Registered(
        val company: BankType,
    ) : CardType
}
