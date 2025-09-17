package woowacourse.payments.ui.catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardCatalogStateHolder(
    initialCardUiState: CardUiState = CardUiState.Empty,
) {
    var cardUiState by mutableStateOf(initialCardUiState)
        private set

    fun addCard(newCard: PaymentCardUiModel) {
        cardUiState = cardUiState.addCard(newCard)
    }

    companion object {
        val Saver: Saver<CardCatalogStateHolder, Any> =
            Saver(
                save = { holder ->
                    holder.cardUiState
                },
                restore = { restoredCardUiState ->
                    CardCatalogStateHolder(restoredCardUiState as CardUiState)
                },
            )
    }
}
