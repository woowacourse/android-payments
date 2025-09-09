package woowacourse.payments.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.validator.CardNumberValidator
import woowacourse.payments.domain.validator.ExpirationDateValidator
import woowacourse.payments.domain.validator.PasswordValidator
import woowacourse.payments.domain.validator.UserNameValidator
import woowacourse.payments.domain.validator.ValidationErrorType
import woowacourse.payments.domain.validator.ValidationResult

class AddCardScreenStateHolder {
    var number by mutableStateOf("")
    var expiration by mutableStateOf("")
    var userName by mutableStateOf("")
    var password by mutableStateOf("")

    var numberErrorType by mutableStateOf<ValidationErrorType?>(null)
    var expirationErrorType by mutableStateOf<ValidationErrorType?>(null)
    var userNameErrorType by mutableStateOf<ValidationErrorType?>(null)
    var passwordErrorType by mutableStateOf<ValidationErrorType?>(null)

    private val cardNumberValidator = CardNumberValidator()
    private val expirationDateValidator = ExpirationDateValidator()
    private val userNameValidator = UserNameValidator()
    private val passwordValidator = PasswordValidator()

    fun onNumberChange(value: String) {
        number = value
    }

    fun onExpirationChange(value: String) {
        expiration = value
    }

    fun onUserNameChange(value: String) {
        userName = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onSaveClick(onAddCard: (Card) -> Unit) {
        val numberResult = cardNumberValidator.validate(number)
        val expirationResult = expirationDateValidator.validate(expiration)
        val userNameResult = userNameValidator.validate(userName)
        val passwordResult = passwordValidator.validate(password)

        numberErrorType = (numberResult as? ValidationResult.Error)?.type
        expirationErrorType = (expirationResult as? ValidationResult.Error)?.type
        userNameErrorType = (userNameResult as? ValidationResult.Error)?.type
        passwordErrorType = (passwordResult as? ValidationResult.Error)?.type

        val isFormValid =
            numberResult is ValidationResult.Success &&
                expirationResult is ValidationResult.Success &&
                passwordResult is ValidationResult.Success &&
                userNameResult is ValidationResult.Success

        if (isFormValid) {
            val card =
                Card(
                    cardNumber = number,
                    expirationDate = expiration,
                    userName = userName,
                    password = password,
                )
            onAddCard(card)
        }
    }
}
