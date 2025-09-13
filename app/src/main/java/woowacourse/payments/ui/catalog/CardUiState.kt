package woowacourse.payments.ui.catalog

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import woowacourse.payments.ui.model.PaymentCardUiModel

sealed class CardUiState {
    data object Empty : CardUiState()

    data class Single(
        val paymentCard: PaymentCardUiModel,
    ) : CardUiState()

    data class Multiple(
        val paymentCards: ImmutableList<PaymentCardUiModel>,
    ) : CardUiState()

    val isAddCardButtonVisible: Boolean = this is Multiple

    fun addCard(newCard: PaymentCardUiModel): CardUiState =
        when (this) {
            Empty -> Single(newCard)
            is Single -> Multiple(persistentListOf(paymentCard, newCard))
            is Multiple -> Multiple((paymentCards + newCard).toImmutableList())
        }
}
