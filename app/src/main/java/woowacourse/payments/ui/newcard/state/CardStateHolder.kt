package woowacourse.payments.ui.newcard.state

import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toDomain
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardStateHolder(
    val previousUiState: CardUiState = CardUiState()
) {
    private val _uiState = mutableStateOf(previousUiState)
    val uiState get() = _uiState

    fun changeBottomSheetState() {
        _uiState.value = _uiState.value.copy(isBottomSheetOpen = !_uiState.value.isBottomSheetOpen)
    }

    fun changeCard(card: Card?) {
        if (card == null) return
        _uiState.value = _uiState.value.copy(
            isBottomSheetOpen = !_uiState.value.isBottomSheetOpen,
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
                    isChangeCardCompany = true
                )
            }
        }
    }

    fun changeNumber(newNumber: String) {
        val sanitized = newNumber.filter { it.isDigit() }.take(16)
        val error = if (sanitized.isEmpty()) null else runCatching {
            CardNumber(value = sanitized)
        }.fold(onSuccess = { null }, onFailure = { it.message })

        _uiState.value =
            _uiState.value.copy(
                number = newNumber,
                numberErrorMessage = error,
                isChangeNumber = true
            )
    }

    fun changeExpiredDate(newExpirationDate: String) {
        val digits = newExpirationDate.filter { it.isDigit() }.take(4)
        val error = if (digits.isEmpty()) null else runCatching {
            ExpiredDate(value = YearMonth.parse(digits, DateTimeFormatter.ofPattern("MMyy")))
        }.fold(onSuccess = { null }, onFailure = { it.message })

        _uiState.value =
            _uiState.value.copy(
                expiredDate = newExpirationDate,
                expirationDateErrorMessage = error,
                isChangeExpirationDate = true
            )
    }

    fun changeOwnerName(newOwnerName: String) {
        val error = if (newOwnerName.isEmpty()) null else runCatching {
            OwnerName(OwnerName(newOwnerName).maxName())
        }.fold(onSuccess = { null }, onFailure = { it.message })

        _uiState.value =
            _uiState.value.copy(ownerName = newOwnerName, ownerNameErrorMessage = error)
    }

    fun changePassword(newPassword: String) {
        val trimmed = newPassword.take(4)
        val error = if (trimmed.isEmpty()) null else runCatching {
            Password(trimmed)
        }.fold(onSuccess = { null }, onFailure = { it.message })

        _uiState.value = _uiState.value.copy(
            password = newPassword, passwordErrorMessage = error, isPassword = true
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