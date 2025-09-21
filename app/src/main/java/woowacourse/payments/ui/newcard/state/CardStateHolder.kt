package woowacourse.payments.ui.newcard.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.newcard.uiModel.CardCompanyUiModel
import woowacourse.payments.ui.newcard.uiModel.toDomain
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardStateHolder (
    val previousUiState: MutableState<CardUiState> = mutableStateOf(CardUiState())
){
    var uiState by previousUiState
        private set
    fun changeBottomSheetState() {
        uiState = uiState.copy(isBottomSheetOpen = !uiState.isBottomSheetOpen)
    }

    fun selectedCardCompany(newCardCompany: CardCompanyUiModel) {
        when (newCardCompany) {
            is CardCompanyUiModel.Default -> {
                uiState = uiState.copy(cardCompany = null)
            }
            is CardCompanyUiModel.SelectCardCompany -> {
                uiState = uiState.copy(cardCompany = newCardCompany.toDomain())
            }
        }
    }

    fun changeNumber(newNumber: String) {
        val sanitized = newNumber.filter { it.isDigit() }.take(16)
        val error = if (sanitized.isEmpty()) null else runCatching {
            CardNumber(value = sanitized)
        }.fold(onSuccess = { null }, onFailure = { it.message })

        uiState = uiState.copy(number = newNumber, numberErrorMessage = error)
    }

    fun changeExpirationDate(newExpirationDate: String) {
        val digits = newExpirationDate.filter { it.isDigit() }.take(4)
        val error = if (digits.isEmpty()) null else runCatching {
            ExpirationDate(value = YearMonth.parse(digits, DateTimeFormatter.ofPattern("MMyy")))
        }.fold(onSuccess = { null }, onFailure = { it.message })

        uiState = uiState.copy(expirationDate = newExpirationDate, expirationDateErrorMessage = error)
    }

    fun changeOwnerName(newOwnerName: String) {
        val error = if (newOwnerName.isEmpty()) null else runCatching {
            OwnerName(OwnerName(newOwnerName).maxName())
        }.fold(onSuccess = { null }, onFailure = { it.message })

        uiState = uiState.copy(ownerName = newOwnerName, ownerNameErrorMessage = error)
    }

    fun changePassword(newPassword: String) {
        val trimmed = newPassword.take(4)
        val error = if (trimmed.isEmpty()) null else runCatching {
            Password(trimmed)
        }.fold(onSuccess = { null }, onFailure = { it.message })

        uiState = uiState.copy(password = newPassword, passwordErrorMessage = error)
    }

    fun newCard(): Card? {
        val company = uiState.cardCompany ?: return null
        return runCatching {
            Card.Companion.Card(
                cardCompany = company,
                number = uiState.number,
                expirationDate = YearMonth.parse(
                    uiState.expirationDate.filter { it.isDigit() }.take(4),
                    DateTimeFormatter.ofPattern("MMyy")
                ),
                ownerName = uiState.ownerName,
                password = uiState.password,
            )
        }.onSuccess { built ->
            uiState = uiState.copy(card = built, cardErrorMessage = null)
        }.onFailure { e ->
            uiState = uiState.copy(card = null, cardErrorMessage = e.message)
        }.getOrNull()
    }
}