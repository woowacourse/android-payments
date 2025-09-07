package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.DefaultPaymentCardValidator
import woowacourse.payments.util.PaymentCardValidator

private const val CARD_EXPIRATION_DATE_LENGTH = 4
private const val CARD_NUMBER_LENGTH = 16
private const val CARD_PASSWORD_LENGTH = 4
private const val CARDHOLDER_NAME_LENGTH = 30

@Composable
fun CardRegistrationScreen(paymentCardValidator: PaymentCardValidator = DefaultPaymentCardValidator()) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val snackbarState = remember { SnackbarHostState() }
    var uiState by rememberSaveable { mutableStateOf(CardRegistrationScreenUiState()) }

    val expiredCardMessage = stringResource(R.string.card_registration_screen_expired_card)
    val navigatePreviousMessage = stringResource(R.string.common_navigate_previous)
    val registerCardMessage =
        stringResource(R.string.card_registration_screen_registration_card_success)

    uiState = uiState.copy(isRegistrableCard = uiState.isRegistrable(paymentCardValidator))

    LaunchedEffect(uiState.snackbarMessage) {
        if (uiState.snackbarMessage.isNullOrBlank()) return@LaunchedEffect
        scope.launch {
            snackbarState.showSnackbar(uiState.snackbarMessage.orEmpty())
            uiState = uiState.copy(snackbarMessage = null)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = { uiState = uiState.copy(snackbarMessage = navigatePreviousMessage) },
                onSaveClick = {
                    focusManager.clearFocus()
                    uiState = uiState.copy(snackbarMessage = registerCardMessage)
                },
                isSaveButtonEnabled = uiState.isRegistrableCard,
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

        PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(40.dp))

        CardNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            cardNumber = uiState.cardNumber,
            onCardNumberChanged = { newValue -> onUiStateChanged(uiState.copy(cardNumber = newValue)) },
            maxLength = CARD_NUMBER_LENGTH,
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
            maxLength = CARD_EXPIRATION_DATE_LENGTH,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardholderNameTextField(
            modifier = Modifier.fillMaxWidth(),
            cardholderName = uiState.cardholderName,
            onCardholderNameChanged = { newValue -> onUiStateChanged(uiState.copy(cardholderName = newValue)) },
            maxLength = CARDHOLDER_NAME_LENGTH,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardPasswordTextField(
            cardPassword = uiState.cardPassword,
            onCardPasswordChanged = { newValue -> onUiStateChanged(uiState.copy(cardPassword = newValue)) },
            maxLength = CARD_PASSWORD_LENGTH,
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
        CardRegistrationScreen()
    }
}
