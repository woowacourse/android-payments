package woowacourse.payments.ui.features.cardinput

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import woowacourse.payments.ui.mapper.CardMapper.getExpireDateUiState
import woowacourse.payments.ui.mapper.CardMapper.toPaymentCardUiModel
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardUiStateHolder(
    initialUiState: CardUiState = CardUiState(),
) {
    private val _uiState = mutableStateOf(initialUiState)
    val uiState: State<CardUiState> = _uiState

    val expireDateUiState: State<ExpireDateUiState> =
        derivedStateOf {
            getExpireDateUiState(_uiState.value.expireDate)
        }

    val paymentCardUiModel: State<PaymentCardUiModel> =
        derivedStateOf {
            _uiState.value.toPaymentCardUiModel()
        }

    fun updateCardCompany(newCardCompany: CardCompanyUiModel) {
        _uiState.value = _uiState.value.copy(cardCompanyUiModel = newCardCompany)
    }

    fun updateCardNumber(newCardNumber: String) {
        _uiState.value = _uiState.value.copy(cardNumber = newCardNumber)
    }

    fun updateExpireDate(newExpireDate: String) {
        _uiState.value = _uiState.value.copy(expireDate = newExpireDate)
    }

    fun updateOwnerName(newOwnerName: String) {
        _uiState.value = _uiState.value.copy(ownerName = newOwnerName)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    companion object {
        val Saver: Saver<CardUiStateHolder, CardUiState> =
            Saver(
                save = { holder -> holder.uiState.value },
                restore = { state -> CardUiStateHolder(initialUiState = state) },
            )
    }
}
