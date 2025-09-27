package woowacourse.payments.view.cardediting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardsRepository
import woowacourse.payments.domain.MockCardsRepository
import woowacourse.payments.view.BankTypeUiModel
import woowacourse.payments.view.toDomain
import java.lang.Character.isDigit

class CardEditingStateHolder(
    initialState: CardEditingUiState,
    private val repository: CardsRepository = MockCardsRepository,
) {
    var uiState by mutableStateOf(initialState)
        private set
    var uiEvent by mutableStateOf<CardEditingUiEvent?>(null)
        private set

    fun updateCardNumber(value: String) {
        val newCardNumber = value.filter(::isDigit).take(CARD_NUMBER_MAX_LENGTH)

        uiState = uiState.copy(edited = uiState.edited.copy(number = newCardNumber))
    }

    fun updateExpiredDate(value: String) {
        val newExpiredDate = value.filter(::isDigit).take(CARD_EXPIRED_DATE_MAX_LENGTH)

        uiState = uiState.copy(edited = uiState.edited.copy(expiredDate = newExpiredDate))
    }

    fun updateHolder(value: String) {
        val holder = value.take(uiState.edited.holderMaxLength).uppercase()

        uiState = uiState.copy(edited = uiState.edited.copy(holder = holder))
    }

    fun updatePassword(value: String) {
        val newPassword = value.filter(::isDigit).take(CARD_PASSWORD_MAX_LENGTH)

        uiState = uiState.copy(edited = uiState.edited.copy(password = newPassword))
    }

    fun updateBankType(newBankType: BankTypeUiModel?) {
        uiState = uiState.copy(edited = uiState.edited.copy(bankType = newBankType))
    }

    fun clearEvent() {
        uiEvent = null
    }

    fun editCard() {
        runCatching {
            repository.editCard(old = uiState.original.toDomain(), new = uiState.edited.toDomain())
        }.onSuccess {
            uiEvent = CardEditingUiEvent.EditCardSuccess
        }.onFailure {
            uiEvent = CardEditingUiEvent.EditCardFailure
        }
    }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH = 16
        private const val CARD_EXPIRED_DATE_MAX_LENGTH = 4
        private const val CARD_PASSWORD_MAX_LENGTH = 4
    }
}

@Composable
fun rememberCardEditingStateHolder(initialState: CardEditingUiState) =
    rememberSaveable(
        saver =
            Saver(
                save = { it.uiState },
                restore = { CardEditingStateHolder(it) },
            ),
    ) { CardEditingStateHolder(initialState) }
