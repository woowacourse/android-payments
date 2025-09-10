package woowacourse.payments.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.payments.Card
import woowacourse.shopping.view.MutableSingleLiveData
import woowacourse.shopping.view.SingleLiveData

class CardsViewModel {
    private val _cards: MutableLiveData<List<Card>> = MutableLiveData()
    val cards: LiveData<List<Card>> get() = _cards

    private val _event: MutableSingleLiveData<CardsUiEvent> = MutableSingleLiveData()
    val event: SingleLiveData<CardsUiEvent> get() = _event

    fun addCard(card: Card) {
        runCatching {
            _cards.value = cards.value?.plus(card) ?: listOf(card)
        }.onSuccess {
            _event.value = CardsUiEvent.AddCardSuccess
        }
    }
}
