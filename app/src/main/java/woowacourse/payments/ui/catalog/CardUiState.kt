package woowacourse.payments.ui.catalog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.parcelize.IgnoredOnParcel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed class CardUiState : Parcelable {
    data object Empty : CardUiState()

    data class Single(
        val paymentCard: PaymentCardUiModel,
    ) : CardUiState()

    data class Multiple(
        val paymentCards: ImmutableList<PaymentCardUiModel>,
    ) : CardUiState()

    @IgnoredOnParcel
    val isAddCardButtonVisible: Boolean = this is Multiple

    fun addCard(newCard: PaymentCardUiModel): CardUiState =
        when (this) {
            Empty -> Single(newCard)
            is Single -> {
                if (paymentCard.order == newCard.order) {
                    Single(newCard)
                } else {
                    Multiple(
                        persistentListOf(paymentCard, newCard)
                            .sortedBy { it.order }
                            .toImmutableList()
                    )
                }
            }
            is Multiple -> {
                val index = paymentCards.indexOfFirst { it.order == newCard.order }
                val updated = if (index >= 0) {
                    paymentCards.toMutableList().apply { this[index] = newCard }
                } else {
                    paymentCards + newCard
                }

                Multiple(updated.sortedBy { it.order }.toImmutableList())
            }
        }
}
