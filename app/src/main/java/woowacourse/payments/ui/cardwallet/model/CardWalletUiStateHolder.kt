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
        get() =
            when (_cards.size) {
                0 -> CardWalletState.EMPTY
                1 -> CardWalletState.SINGLE
                else -> CardWalletState.MULTIPLE
            }

    fun onCardAdded(card: CardUiModel?): Boolean {
        if (card == null) return false
        _cards.add(card)
        return true
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
