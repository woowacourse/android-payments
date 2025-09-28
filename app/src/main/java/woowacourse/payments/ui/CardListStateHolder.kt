package woowacourse.payments.ui

import androidx.activity.result.ActivityResult
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import woowacourse.payments.AddcardActivity
import woowacourse.payments.EditcardActivity
import woowacourse.payments.R
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardListStateHolder {
    private val _cardUiModels = mutableStateListOf<PaymentCardUiModel>()
    val cardUiModels: List<PaymentCardUiModel> = _cardUiModels
    private val _uiEventFlow = Channel<CardListUiEvent>()
    val uiEventFlow = _uiEventFlow.receiveAsFlow()

    suspend fun onAddCardResult(activityResult: ActivityResult) {
        val newCard = AddcardActivity.getPaymentCardUiModelByAddCard(activityResult) ?: return
        _cardUiModels.add(newCard)
        _uiEventFlow.send(CardListUiEvent.ShowToast(R.string.card_list_card_added_alert))
    }

    suspend fun onEditCardResult(activityResult: ActivityResult) {
        val editedCard = EditcardActivity.getPaymentCardUiModelByEditCard(activityResult) ?: return
        val index = _cardUiModels.indexOfFirst { it.dbId == editedCard.dbId }
        if (index != -1) {
            _cardUiModels[index] = editedCard
            _uiEventFlow.send(CardListUiEvent.ShowToast(R.string.card_list_card_edited_alert))
        }
    }

    companion object {
        val Saver: Saver<CardListStateHolder, *> =
            listSaver(
                save = { stateHolder -> stateHolder.cardUiModels },
                restore = { savedList ->
                    CardListStateHolder().apply {
                        _cardUiModels.addAll(savedList)
                    }
                },
            )
    }
}
