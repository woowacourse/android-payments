package woowacourse.payments.ui.catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CardUiStateHolder {
    private var _cardUiState by mutableStateOf(CardUiState.EMPTY)
    val cardUiState: CardUiState get() = _cardUiState

    fun addCard() {
        _cardUiState = when (cardUiState) {
            CardUiState.EMPTY -> CardUiState.SINGLE
            CardUiState.SINGLE -> CardUiState.MUTIPLE
            CardUiState.MUTIPLE -> CardUiState.MUTIPLE
        }
    }
}