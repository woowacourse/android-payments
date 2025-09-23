package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.saveable.Saver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.ui.extension.update
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsScreenViewModel(
    initialUiState: CardsUiState = CardsUiState.EMPTY,
) {
    private val _uiState = MutableLiveData(initialUiState)
    val uiState: LiveData<CardsUiState> = _uiState

    private val _uiEvent = MutableLiveData<CardsScreenUiEvent?>()
    val uiEvent: LiveData<CardsScreenUiEvent?> = _uiEvent.also { _uiEvent.value = null }

    fun addCard(newCard: PaymentCardUiModel) {
        _uiState.update { addCard(newCard) }
        _uiEvent.value = CardsScreenUiEvent.RegisteredCard
    }

    companion object {
        val saver: Saver<CardsScreenViewModel, CardsUiState> =
            Saver(
                save = { viewModel -> viewModel.uiState.value ?: CardsUiState.EMPTY },
                restore = { uiState -> CardsScreenViewModel(uiState) },
            )
    }
}
