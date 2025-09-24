package woowacourse.payments.newCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardExpiry
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword

class NewCardState {
    var cardNumber by mutableStateOf("")
        private set
    var numberErrorMessage by mutableStateOf("")
        private set
    var isNumberError by mutableStateOf(false)
        private set

    var cardExpiry by mutableStateOf("")
        private set
    var expiryErrorMessage by mutableStateOf("")
        private set
    var isExpiryError by mutableStateOf(false)
        private set

    var cardPassword by mutableStateOf("")
        private set
    var passwordErrorMessage by mutableStateOf("")
        private set
    var isPasswordError by mutableStateOf(false)
        private set

    var cardName by mutableStateOf("")
        private set

    fun onNumberChange(newNumber: String) {
        cardNumber = newNumber

        val validation = runCatching { CardNumber(newNumber) }
        validation.onFailure { result ->
            numberErrorMessage = result.message ?: ""
            isNumberError = true
        }
        validation.onSuccess {
            numberErrorMessage = ""
            isNumberError = false
        }
    }

    fun onExpiryChange(newExpiry: String) {
        cardExpiry = newExpiry

        val validation = runCatching { CardExpiry.fromString(newExpiry) }
        validation.onFailure { result ->
            expiryErrorMessage = result.message ?: ""
            isExpiryError = true
        }
        validation.onSuccess {
            expiryErrorMessage = ""
            isExpiryError = false
        }
    }

    fun onPasswordChange(newPassword: String) {
        cardPassword = newPassword

        val validation = runCatching { CardPassword(newPassword) }
        validation.onFailure { result ->
            passwordErrorMessage = result.message ?: ""
            isPasswordError = true
        }
        validation.onSuccess {
            passwordErrorMessage = ""
            isPasswordError = false
        }
    }

    fun onNameChange(new: String) {
        cardName = new
    }
}
