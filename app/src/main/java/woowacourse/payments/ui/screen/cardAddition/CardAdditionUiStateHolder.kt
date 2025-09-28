package woowacourse.payments.ui.screen.cardAddition

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.IssuingBank
import woowacourse.payments.ui.model.toUiState

class CardAdditionUiStateHolder(
    private val originalCard: CardUiModel? = null,
) {
    var uiState by mutableStateOf(originalCard?.toUiState() ?: CardAdditionUiState.EMPTY_CARD)
        private set
    var hasShownSheet by mutableStateOf(false)
        private set
    val card by derivedStateOf { uiState.toUiModel() }
    val isCompletable by derivedStateOf { uiState.isValidCard && (originalCard == null || originalCard != card) }

    fun updateCardState(
        newCardNumber: String? = null,
        newExpiredDate: String? = null,
        newOwnerName: String? = null,
        newPassword: String? = null,
        newIssuingBank: IssuingBank? = null,
    ) {
        uiState =
            uiState.update(
                newCardNumber = newCardNumber,
                newExpiredDate = newExpiredDate,
                newOwnerName = newOwnerName,
                newPassword = newPassword,
                newIssuingBank = newIssuingBank,
            )
    }

    fun updateSheetVisible() {
        hasShownSheet = hasShownSheet.not()
    }

    companion object {
        val Saver: Saver<CardAdditionUiStateHolder, CardAdditionUiState> =
            Saver(
                save = { stateHolder -> stateHolder.uiState },
                restore = { uiState -> CardAdditionUiStateHolder(uiState.toUiModel()) },
            )
    }
}
