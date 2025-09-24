package woowacourse.payments.view.cardaddition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardsRepository
import woowacourse.payments.domain.MockCardsRepository
import woowacourse.payments.view.BankTypeUiModel
import woowacourse.payments.view.toDomain
import java.lang.Character.isDigit

class CardAdditionStateHolder(
    initialState: CardAdditionUiState = CardAdditionUiState(),
    private val repository: CardsRepository = MockCardsRepository,
) {
    var uiState: CardAdditionUiState by mutableStateOf(initialState)
        private set
    var uiEvent by mutableStateOf<CardAdditionUiEvent?>(null)
        private set

    fun updateCardNumber(value: String) {
        val newCardNumber: String = value.filter(::isDigit).take(Card.NUMBER_LENGTH)

        uiState = uiState.copy(card = uiState.card.copy(number = newCardNumber))
    }

    fun updateExpiredDate(value: String) {
        val newExpiredDate: String = value.filter(::isDigit).take(Card.EXPIRED_DATE_LENGTH)

        uiState = uiState.copy(card = uiState.card.copy(expiredDate = newExpiredDate))
    }

    fun updateHolder(value: String) {
        val holder: String = value.take(uiState.card.holderMaxLength).uppercase()

        uiState = uiState.copy(card = uiState.card.copy(holder = holder))
    }

    fun updatePassword(value: String) {
        val newPassword: String = value.filter(::isDigit).take(Card.PASSWORD_LENGTH)

        uiState = uiState.copy(card = uiState.card.copy(password = newPassword))
    }

    fun updateBankType(newBankType: BankTypeUiModel?) {
        uiState = uiState.copy(card = uiState.card.copy(bankType = newBankType))
    }

    fun clearEvent() {
        uiEvent = null
    }

    fun addCard() {
        runCatching {
            repository.addCard(uiState.card.toDomain())
        }.onSuccess {
            uiEvent = CardAdditionUiEvent.AddCardSuccess
        }.onFailure {
            uiEvent = CardAdditionUiEvent.AddCardFailure
        }
    }
}

@Composable
fun rememberCardAdditionStateHolder(initialState: CardAdditionUiState = CardAdditionUiState()) =
    rememberSaveable(
        saver =
            Saver(
                save = { stateHolder: CardAdditionStateHolder -> stateHolder.uiState },
                restore = { state: CardAdditionUiState -> CardAdditionStateHolder(state) },
            ),
    ) { CardAdditionStateHolder(initialState) }
