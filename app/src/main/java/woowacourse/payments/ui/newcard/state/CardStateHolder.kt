package woowacourse.payments.ui.newcard.state

import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toDomain
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardStateHolder(
    val previousUiState: CardUiState = CardUiState()
) {
    private val _uiState = mutableStateOf(previousUiState)
    val uiState get() = _uiState

    fun changeCard(card: Card?) {
        if (card == null) return
        _uiState.value = _uiState.value.copy(
            card = card,
            cardCompany = card.cardCompany,
            expiredDate = card.expiredDate.value
                .format(DateTimeFormatter.ofPattern("MMyy")),
            number = card.number.value,
            ownerName = card.ownerName.value ?: "",
            password = card.password.value,
        )
    }

    fun selectedCardCompany(newCardCompany: CardCompanyUiModel) {
        when (newCardCompany) {
            is CardCompanyUiModel.Default -> {
                _uiState.value = _uiState.value.copy(cardCompany = null)
            }

            is CardCompanyUiModel.SelectCardCompany -> {
                _uiState.value = _uiState.value.copy(
                    cardCompany = newCardCompany.toDomain(),
                )
            }
        }
    }

    fun changeNumber(newNumber: String) {
        _uiState.value =
            _uiState.value.copy(number = newNumber)
    }

    fun changeExpiredDate(newExpirationDate: String) {
        _uiState.value =
            _uiState.value.copy(
                expiredDate = newExpirationDate
            )
    }

    fun changeOwnerName(newOwnerName: String) {
        _uiState.value = _uiState.value.copy(ownerName = newOwnerName)
    }

    fun changePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(
            password = newPassword
        )
    }

    fun newCard(): Card? {
        val company = _uiState.value.cardCompany ?: return null
        return runCatching {
            Card.Companion.Card(
                cardCompany = company,
                number = _uiState.value.number,
                expiredDate = YearMonth.parse(
                    _uiState.value.expiredDate.filter { it.isDigit() }.take(4),
                    DateTimeFormatter.ofPattern("MMyy")
                ),
                ownerName = _uiState.value.ownerName,
                password = _uiState.value.password,
            )
        }.onSuccess { built ->
            val old = _uiState.value.card
            val changed = old == null || old.cardCompany != company ||
                    old.number.value != _uiState.value.number ||
                    old.expiredDate.value != YearMonth.parse(
                _uiState.value.expiredDate.filter { it.isDigit() }.take(4),
                DateTimeFormatter.ofPattern("MMyy")
            ) ||
                    (old.ownerName.value ?: "") != _uiState.value.ownerName ||
                    old.password.value != _uiState.value.password

            if (changed) {
                _uiState.value = _uiState.value.copy(card = built, cardErrorMessage = null)
            }

        }.onFailure { e ->
            _uiState.value = _uiState.value.copy(card = null, cardErrorMessage = e.message)
        }.getOrNull()
    }
}