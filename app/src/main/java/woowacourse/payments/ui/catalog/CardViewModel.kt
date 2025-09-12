package woowacourse.payments.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardViewModel(
    initialCardUiState: CardUiState = CardUiState.Empty,
) {
    private var _cardUiState: MutableLiveData<CardUiState> = MutableLiveData(initialCardUiState)
    val cardUiState: LiveData<CardUiState> = _cardUiState

    fun addCard(newCard: PaymentCardUiModel) {
        _cardUiState.value = cardUiState.value?.addCard(newCard) ?: CardUiState.Empty
    }
}
