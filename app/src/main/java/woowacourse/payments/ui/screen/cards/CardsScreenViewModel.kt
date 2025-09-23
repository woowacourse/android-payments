package woowacourse.payments.ui.screen.cards

import android.util.Log
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
        Log.i("TEST", "addCard")
        updateState(newCard)
        _uiEvent.value = CardsScreenUiEvent.RegisteredCard
    }

    fun updateCard(updatedCard: PaymentCardUiModel) {
        Log.i("TEST", "updateCard")
        updateState(updatedCard)
        _uiEvent.value = CardsScreenUiEvent.UpdatedCard
    }

    private fun updateState(card: PaymentCardUiModel) {
        _uiState.update { addCard(card) }
    }

    companion object {
        val saver: Saver<CardsScreenViewModel, CardsUiState> =
            Saver(
                save = { viewModel -> viewModel.uiState.value ?: CardsUiState.EMPTY },
                restore = { uiState -> CardsScreenViewModel(uiState) },
            )
    }
}
