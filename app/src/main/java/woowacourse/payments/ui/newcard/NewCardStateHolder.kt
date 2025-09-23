package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.ui.model.PaymentCardUiModel

class NewCardStateHolder {
    private var _id = 0
    val id get() = _id

    private var _cardNumber by mutableStateOf("")
    val cardNumber get() = _cardNumber

    private var _cardHolder by mutableStateOf("")
    val cardHolder get() = _cardHolder

    val expirationDateUiState = ExpirationDateUiState("")

    private var _password by mutableStateOf("")
    val password get() = _password

    private var _bank by mutableStateOf(Bank())
    val bank get() = _bank

    fun updateCardNumber(newCardNumber: String) {
        _cardNumber = newCardNumber
    }

    fun updateCardHolder(newCardHolder: String) {
        _cardHolder = newCardHolder
    }

    fun updateBank(newBank: Bank) {
        _bank = newBank
    }

    fun updatePassword(newPassword: String) {
        _password = newPassword
    }

    fun updateId(newId: Int) {
        _id = newId
    }

    fun isModified(initialCard: PaymentCardUiModel): Boolean =
        cardNumber != initialCard.cardNumber.value || cardHolder != initialCard.cardHolder.value ||
            expirationDateUiState.expirationDate.value != initialCard.expirationDate.value
}
