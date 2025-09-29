package woowacourse.payments.ui.newcard.state

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toDomain
import woowacourse.payments.ui.model.toUiModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Parcelize
class CardStateHolder(
    val previousUiState: CardUiState = CardUiState()
) : Parcelable {
    private val _uiState = mutableStateOf(previousUiState)
    val uiState get() = _uiState

    fun changeCard(card: Card?) {
        if (card == null) return
        _uiState.value = _uiState.value.copy(
            cardCompanyUiModel = card.cardCompany.toUiModel(),
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
                _uiState.value =
                    _uiState.value.copy(cardCompanyUiModel = CardCompanyUiModel.Default)
            }

            is CardCompanyUiModel.SelectCardCompany -> {
                _uiState.value = _uiState.value.copy(
                    cardCompanyUiModel = newCardCompany,
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

    fun newCard(card: Card?): Card? {
        val company = _uiState.value.cardCompanyUiModel
        return runCatching {
            Card.Companion.Card(
                cardCompany = company.toDomain(),
                number = _uiState.value.number,
                expiredDate = YearMonth.parse(
                    _uiState.value.expiredDate.filter { it.isDigit() }.take(4),
                    DateTimeFormatter.ofPattern("MMyy")
                ),
                ownerName = _uiState.value.ownerName,
                password = _uiState.value.password,
            )
        }.onSuccess { built ->
            val old = card
            val changed = old == null || old.cardCompany != company.toDomain() ||
                    old.number.value != _uiState.value.number ||
                    old.expiredDate.value != YearMonth.parse(
                _uiState.value.expiredDate.filter { it.isDigit() }.take(4),
                DateTimeFormatter.ofPattern("MMyy")
            ) ||
                    (old.ownerName.value ?: "") != _uiState.value.ownerName ||
                    old.password.value != _uiState.value.password

            if (changed) {
                _uiState.value = _uiState.value.copy(cardErrorMessage = null)
            }

        }.onFailure { e ->
            _uiState.value = _uiState.value.copy(cardErrorMessage = e.message)
        }.getOrNull()
    }
}