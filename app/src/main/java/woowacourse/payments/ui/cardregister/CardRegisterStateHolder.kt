package woowacourse.payments.ui.cardregister

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.BankViewType

class CardRegisterStateHolder {
    var cardNumber by mutableStateOf("")
        private set

    var expiryDate by mutableStateOf("")
        private set

    var cardOwner by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var selectedBankViewType by mutableStateOf(BankViewType.NONE)
        private set

    val isCardNumberError by derivedStateOf {
        cardNumber.isNotEmpty() && cardNumber.length != CARD_NUMBER_MAX_LENGTH
    }

    val isExpiryDateError by derivedStateOf {
        expiryDate.isNotEmpty() && expiryDate.length != EXPIRY_DATE_MAX_LENGTH
    }

    val isCardOwnerError by derivedStateOf {
        cardOwner.length > CARD_OWNER_MAX_LENGTH
    }

    val isPasswordError by derivedStateOf {
        password.isNotEmpty() && password.length != PASSWORD_MAX_LENGTH
    }

    fun onCardNumberChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= CARD_NUMBER_MAX_LENGTH) cardNumber = filteredText
    }

    fun onExpiryDateChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= EXPIRY_DATE_MAX_LENGTH) expiryDate = filteredText
    }

    fun onCardOwnerChange(newValue: String) {
        if (newValue.length <= CARD_OWNER_MAX_LENGTH) cardOwner = newValue
    }

    fun onPasswordChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= PASSWORD_MAX_LENGTH) password = filteredText
    }

    fun onSelectedBankViewTypeChange(newValue: BankViewType) {
        selectedBankViewType = newValue
    }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH: Int = 16
        private const val EXPIRY_DATE_MAX_LENGTH: Int = 4
        private const val CARD_OWNER_MAX_LENGTH: Int = 30
        private const val PASSWORD_MAX_LENGTH: Int = 4
    }
}
