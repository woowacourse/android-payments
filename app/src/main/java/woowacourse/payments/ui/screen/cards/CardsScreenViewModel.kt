package woowacourse.payments.ui.screen.cards

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.ui.extension.update
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsScreenViewModel(
    initialUiState: CardsUiState = CardsUiState.EMPTY,
) {
    private val _uiState = MutableLiveData(initialUiState)
    val uiState: LiveData<CardsUiState> = _uiState

    private val _uiEvent = MutableLiveData<CardsScreenUiEvent>()
    val uiEvent: LiveData<CardsScreenUiEvent> =
        _uiEvent.also { _uiEvent.value = CardsScreenUiEvent.None }

    fun addCard(newCard: PaymentCardUiModel) {
        _uiState.update { addCard(newCard).also { Log.i("TEST", "addCard: $it") } }
        _uiEvent.value = CardsScreenUiEvent.RegisteredCard
    }
}
