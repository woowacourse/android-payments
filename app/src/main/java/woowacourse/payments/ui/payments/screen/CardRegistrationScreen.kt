package woowacourse.payments.ui.payments.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.DefaultPaymentCardValidator
import woowacourse.payments.domain.InputType
import woowacourse.payments.domain.PaymentCardValidator
import woowacourse.payments.ui.common.component.PaymentCardField
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.CardRegistrationScreenUiState
import woowacourse.payments.ui.payments.component.CardExpirationDateTextField
import woowacourse.payments.ui.payments.component.CardNumberTextField
import woowacourse.payments.ui.payments.component.CardPasswordTextField
import woowacourse.payments.ui.payments.component.CardRegistrationTopAppBar
import woowacourse.payments.ui.payments.component.CardholderNameTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(
    paymentCardValidator: PaymentCardValidator = DefaultPaymentCardValidator(),
    onCardRegistered: (PaymentCardUiModel) -> Unit,
    onBackPressed: () -> Unit,
) {
    var uiState by rememberSaveable { mutableStateOf(CardRegistrationScreenUiState()) }
    val isRegistrableCard by remember {
        derivedStateOf { uiState.isRegistrable(paymentCardValidator) }
    }
    val expiredCardMessage = stringResource(R.string.card_registration_screen_expired_card)

    Scaffold(
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = {
                    onBackPressed()
                },
                onSaveClick = {
                    onCardRegistered(
                        PaymentCardUiModel(
                            cardNumber = uiState.cardNumber,
                            cardExpirationDate = uiState.cardExpirationDate,
                            cardholderName = uiState.cardholderName,
                        )
                    )
                },
                isSaveButtonEnabled = isRegistrableCard,
            )
        },
    ) { innerPadding ->
        CardRegistrationScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onUiStateChanged = { newUiState -> uiState = newUiState },
            paymentCardValidator = paymentCardValidator,
            expiredCardMessage = expiredCardMessage,
        )
    }
}

@Composable
private fun CardRegistrationScreenContent(
    modifier: Modifier,
    uiState: CardRegistrationScreenUiState,
    onUiStateChanged: (CardRegistrationScreenUiState) -> Unit,
    paymentCardValidator: PaymentCardValidator,
    expiredCardMessage: String,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        PaymentCardField(modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(40.dp))

        CardNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            cardNumber = uiState.cardNumber,
            onCardNumberChanged = { newValue -> onUiStateChanged(uiState.copy(cardNumber = newValue)) },
            maxLength = InputType.CardNumber.maxLength,
        )

        Spacer(modifier = Modifier.height(24.dp))

        CardExpirationDateTextField(
            cardExpirationDate = uiState.cardExpirationDate,
            onCardExpirationDateChanged = { newValue ->
                val isValid =
                    newValue.length != 4 || paymentCardValidator.validateCardExpirationDate(newValue)
                onUiStateChanged(
                    uiState.copy(
                        cardExpirationDate = newValue,
                        cardExpirationDateErrorMessage = if (!isValid) expiredCardMessage else null,
                    ),
                )
            },
            errorMessage = uiState.cardExpirationDateErrorMessage,
            onErrorMessageChanged = { errorMsg ->
                val newUiState = uiState.copy(cardExpirationDateErrorMessage = errorMsg)
                onUiStateChanged(newUiState)
            },
            maxLength = InputType.ExpiryDate.maxLength,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardholderNameTextField(
            modifier = Modifier.fillMaxWidth(),
            cardholderName = uiState.cardholderName,
            onCardholderNameChanged = { newValue -> onUiStateChanged(uiState.copy(cardholderName = newValue)) },
            maxLength = InputType.CardholderName.maxLength,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardPasswordTextField(
            cardPassword = uiState.cardPassword,
            onCardPasswordChanged = { newValue -> onUiStateChanged(uiState.copy(cardPassword = newValue)) },
            maxLength = InputType.Password.maxLength,
        )
    }
}

private fun CardRegistrationScreenUiState.isRegistrable(paymentCardValidator: PaymentCardValidator): Boolean =
    paymentCardValidator.validateCardNumber(cardNumber) &&
            paymentCardValidator.validateCardExpirationDate(cardExpirationDate) &&
            paymentCardValidator.validateCardholderName(cardholderName) &&
            paymentCardValidator.validateCardPassword(cardPassword)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardRegistrationScreenPreview() {
    AndroidpaymentsTheme {
        CardRegistrationScreen(onBackPressed = {}, onCardRegistered = {})
    }
}
