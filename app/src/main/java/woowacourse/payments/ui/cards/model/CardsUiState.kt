package woowacourse.payments.ui.cards.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed interface CardsUiState : Parcelable {
    data object None : CardsUiState

    data class Single(
        val card: PaymentCardUiModel,
    ) : CardsUiState

    data class Multiple(
        val cards: List<PaymentCardUiModel>,
    ) : CardsUiState

    companion object {
        fun of(paymentCards: List<PaymentCardUiModel>): CardsUiState =
            when (paymentCards.size) {
                0 -> None
                1 -> Single(paymentCards.first())
                else -> Multiple(paymentCards)
            }
    }
}
