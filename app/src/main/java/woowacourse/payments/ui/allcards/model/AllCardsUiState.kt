package woowacourse.payments.ui.allcards.model

import android.os.Parcelable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.uimodel.CardInfoUiState

@Parcelize
data class AllCardsUiState(
    private val _cards: SnapshotStateList<CardInfoUiState>,
) : Parcelable {
    constructor(cards: List<CardInfoUiState>) : this(cards.toMutableStateList())

    val cards: List<CardInfoUiState> get() = _cards
    val viewType: ViewType
        get() =
            when (_cards.size) {
                0 -> ViewType.EMPTY
                1 -> ViewType.SINGLE
                else -> ViewType.MULTIPLE
            }

    fun addCard(card: CardInfoUiState) {
        _cards.add(card)
    }

    fun modifyCard(
        cardInfo: CardInfoUiState,
        id: Long,
    ) {
        val idx = _cards.indexOfFirst { it.id == id }
        _cards[idx] = cardInfo
    }

    enum class ViewType {
        EMPTY,
        SINGLE,
        MULTIPLE,
    }
}
