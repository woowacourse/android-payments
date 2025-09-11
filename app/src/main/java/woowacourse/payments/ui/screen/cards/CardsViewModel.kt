package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsViewModel(
    initialUiState: CardsUiState = CardsUiState.EMPTY,
) {
    private var _uiState by mutableStateOf(initialUiState)
    val uiState: CardsUiState get() = _uiState

    private var _uiEvent by mutableStateOf<CardsScreenUiEvent>(CardsScreenUiEvent.None)
    val uiEvent: CardsScreenUiEvent get() = _uiEvent.also { _uiEvent = CardsScreenUiEvent.None }

    fun addCard(newCard: PaymentCardUiModel) {
        _uiState = _uiState.addCard(newCard)
        _uiEvent = CardsScreenUiEvent.RegisteredCard
    }
}
