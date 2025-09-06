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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.DefaultPaymentCardValidator
import woowacourse.payments.util.PaymentCardValidator

@Composable
fun CardRegistrationScreen(paymentCardValidator: PaymentCardValidator = DefaultPaymentCardValidator()) {
    val focusManager = LocalFocusManager.current
    val snackbarState = remember { SnackbarHostState() }
    var uiState by rememberSaveable { mutableStateOf(CardRegistrationScreenUiState()) }

    val expiredCardMessage = stringResource(R.string.card_registration_screen_expired_card)
    val navigatePreviousMessage = stringResource(R.string.common_navigate_previous)
    val registerCardMessage =
        stringResource(R.string.card_registration_screen_registration_card_success)

    uiState = uiState.copy(isRegistrableCard = isRegistrableCard(uiState, paymentCardValidator))
    LaunchedEffect(uiState.snackbarMessage) {
        if (uiState.snackbarMessage.isNullOrBlank()) return@LaunchedEffect
        snackbarState.showSnackbar(uiState.snackbarMessage.orEmpty())
        uiState = uiState.copy(snackbarMessage = null)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = {
                    uiState = uiState.copy(snackbarMessage = navigatePreviousMessage)
                },
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
            onCardNumberChanged = { newCardNumber ->
                uiState = uiState.copy(cardNumber = newCardNumber)
            },
            onCardExpirationDateChanged = { newCardExpirationDate ->
                val isCardExpirationDateValid =
                    if (newCardExpirationDate.length == 4) {
                        paymentCardValidator.validateCardExpirationDate(newCardExpirationDate)
                    } else {
                        true
                    }
                uiState =
                    uiState.copy(
                        cardExpirationDate = newCardExpirationDate,
                        cardExpirationDateErrorMessage = if (!isCardExpirationDateValid) expiredCardMessage else null,
                    )
            },
            onCardExpirationDateErrorMessageChanged = { errorMessage ->
                uiState = uiState.copy(cardExpirationDateErrorMessage = errorMessage)
            },
            onCardholderNameChanged = { newCardholderName ->
                uiState = uiState.copy(cardholderName = newCardholderName)
            },
            onCardPasswordChanged = { newCardPassword ->
                uiState = uiState.copy(cardPassword = newCardPassword)
            },
        )
    }
}

@Composable
private fun CardRegistrationScreenContent(
    modifier: Modifier,
    uiState: CardRegistrationScreenUiState,
    onCardNumberChanged: (String) -> Unit,
    onCardExpirationDateChanged: (String) -> Unit,
    onCardExpirationDateErrorMessageChanged: (String?) -> Unit,
    onCardholderNameChanged: (String) -> Unit,
    onCardPasswordChanged: (String) -> Unit,
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
            onCardNumberChanged = onCardNumberChanged,
        )

        Spacer(modifier = Modifier.height(24.dp))

        CardExpirationDateTextField(
            cardExpirationDate = uiState.cardExpirationDate,
            onCardExpirationDateChanged = onCardExpirationDateChanged,
            errorMessage = uiState.cardExpirationDateErrorMessage,
            onErrorMessageChanged = onCardExpirationDateErrorMessageChanged,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardholderNameTextField(
            modifier = Modifier.fillMaxWidth(),
            cardholderName = uiState.cardholderName,
            onCardholderNameChanged = onCardholderNameChanged,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardPasswordTextField(
            cardPassword = uiState.cardPassword,
            onCardPasswordChanged = onCardPasswordChanged,
        )
    }
}

private fun isRegistrableCard(
    uiState: CardRegistrationScreenUiState,
    paymentCardValidator: PaymentCardValidator,
): Boolean =
    paymentCardValidator.validateCardNumber(uiState.cardNumber) &&
        paymentCardValidator.validateCardExpirationDate(uiState.cardExpirationDate) &&
        paymentCardValidator.validateCardholderName(uiState.cardholderName) &&
        paymentCardValidator.validateCardPassword(uiState.cardPassword)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardRegistrationScreenPreview() {
    AndroidpaymentsTheme {
        CardRegistrationScreen()
    }
}
