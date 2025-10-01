package woowacourse.payments.ui.cardwallet.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardUiModel

class CardWalletUiStateHolder(
    initialCards: List<CardUiModel> = emptyList(),
) {
    private val _cards = mutableStateListOf<CardUiModel>().apply { addAll(initialCards) }
    val cards: List<CardUiModel> get() = _cards

    val cardCount: Int
        get() = _cards.size

    val cardWalletState: CardWalletState
        get() = CardWalletState.from(cardCount)

    fun updateCard(card: CardUiModel): CardChangeResult {
        val index = _cards.indexOfFirst { it.id == card.id }
        return if (index != -1) {
            _cards[index] = card
            CardChangeResult.Edited(card)
        } else {
            _cards.add(card)
            CardChangeResult.Created(card)
        }
    }

    companion object {
        val Saver: Saver<CardWalletUiStateHolder, ArrayList<CardUiModel>> =
            Saver(
                save = { holder -> ArrayList(holder.cards) },
                restore = { saved -> CardWalletUiStateHolder(saved) },
            )
    }
}

@Composable
fun rememberCardWalletState(initialCards: List<CardUiModel> = emptyList()): CardWalletUiStateHolder =
    rememberSaveable(saver = CardWalletUiStateHolder.Saver) {
        CardWalletUiStateHolder(initialCards)
    }
