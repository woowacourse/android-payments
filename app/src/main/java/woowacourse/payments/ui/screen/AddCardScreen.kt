package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.toUiModel
import woowacourse.payments.domain.validator.CardNumberValidator
import woowacourse.payments.domain.validator.ExpirationDateValidator
import woowacourse.payments.domain.validator.PasswordValidator
import woowacourse.payments.domain.validator.UserNameValidator
import woowacourse.payments.domain.validator.ValidationErrorType
import woowacourse.payments.domain.validator.ValidationResult
import woowacourse.payments.ui.components.CardNumberField
import woowacourse.payments.ui.components.ExpirationDateField
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PasswordField
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.UserNameField
import woowacourse.payments.ui.strings.getErrorMessage

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
) {
    var number by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var numberErrorType by remember { mutableStateOf<ValidationErrorType?>(null) }
    var expirationErrorType by remember { mutableStateOf<ValidationErrorType?>(null) }
    var userNameErrorType by remember { mutableStateOf<ValidationErrorType?>(null) }
    var passwordErrorType by remember { mutableStateOf<ValidationErrorType?>(null) }

    val cardNumberValidator = remember { CardNumberValidator() }
    val expirationDateValidator = remember { ExpirationDateValidator() }
    val userNameValidator = remember { UserNameValidator() }
    val passwordValidator = remember { PasswordValidator() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
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
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(Modifier.height(14.dp))
            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                card = Card("", "", "", "").toUiModel(),
            )

            Spacer(Modifier.height(40.dp))
            CardNumberField(
                value = number,
                onValueChange = { number = it },
                modifier = Modifier.fillMaxWidth(),
                isError = numberErrorType != null,
                errorMessage = numberErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(30.dp))
            ExpirationDateField(
                value = expiration,
                onValueChange = { expiration = it },
                modifier = Modifier.fillMaxWidth(0.5f),
                isError = expirationErrorType != null,
                errorMessage = expirationErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(30.dp))
            UserNameField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
                isError = userNameErrorType != null,
                errorMessage = userNameErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(0.5f),
                isError = passwordErrorType != null,
                errorMessage = passwordErrorType?.let { getErrorMessage(it) },
            )
        }
    }
}

@Preview
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        onBackPressed = {},
        onAddCard = {},
    )
}
