package woowacourse.payments.ui.screen.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed interface CardsUiState : Parcelable {
    data object EMPTY : CardsUiState

    data class SINGLE(
        val card: PaymentCardUiModel,
    ) : CardsUiState

    data class MULTIPLE(
        val cards: List<PaymentCardUiModel>,
    ) : CardsUiState

    fun addCard(newCard: PaymentCardUiModel): CardsUiState =
        when (this) {
            EMPTY -> SINGLE(newCard)

            is SINGLE ->
                when {
                    newCard.id == card.id -> SINGLE(newCard)
                    else -> MULTIPLE(listOf(card, newCard))
                }

            is MULTIPLE -> {
                val newCards = cards.map { card -> if (card.id == newCard.id) newCard else card }
                MULTIPLE(newCards)
            }
        }
}
