package woowacourse.payments.ui.card.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

class RegisterCardStateHolder(
    private val onCardSaved: (newCardUiModel: CardUiModel) -> Unit,
) {
    var uiState by mutableStateOf(RegisterCardUiState())
        private set

    fun updateCardNumber(cardNumber: String) {
        uiState = uiState.copy(cardNumber = cardNumber)
    }

    fun updateExpirationDate(expirationDate: String) {
        uiState = uiState.copy(expirationDate = expirationDate)
    }

    fun updateCardHolderName(cardHolderName: String) {
        uiState = uiState.copy(cardHolderName = cardHolderName)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateSelectedBank(bank: Bank) {
        uiState = uiState.copy(selectedBank = bank, showBottomSheet = false)
    }

    fun clearToastMessage() {
        uiState = uiState.copy(toastMessage = null)
    }

    fun saveCard() {
        createCard()
            .onSuccess { newCard ->
                onCardSaved(newCard.toUiModel())
                uiState = uiState.copy(toastMessage = "카드 생성에 성공했습니다.")
            }.onFailure { errorMessage ->
                uiState = uiState.copy(toastMessage = errorMessage.message ?: "카드 생성에 실패했습니다.")
            }
    }

    private fun createCard(): Result<Card> {
        val bank = uiState.selectedBank ?: return Result.failure(IllegalArgumentException())

        return Card
            .newCard(
                number = uiState.cardNumber,
                expirationDate = uiState.expirationDate,
                cardHolderName = uiState.cardHolderName,
                password = uiState.password,
                bank = bank,
            )
    }
}
