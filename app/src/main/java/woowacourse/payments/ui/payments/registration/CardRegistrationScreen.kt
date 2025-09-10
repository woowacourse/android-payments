package woowacourse.payments.ui.payments.registration

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.payments.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(
    onBackPressed: () -> Unit,
    onCardRegistered: (CardUiModel) -> Unit,
) {
    val snackbarState = remember { SnackbarHostState() }
    var uiState by rememberSaveable { mutableStateOf(CardRegistrationScreenUiState()) }

    val navigatePreviousMessage = stringResource(R.string.common_navigate_previous)
    val registerCardMessage =
        stringResource(R.string.card_registration_screen_registration_card_success)

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
                    onBackPressed()
                    uiState = uiState.copy(snackbarMessage = navigatePreviousMessage)
                },
                onSaveClick = {
                    onCardRegistered(
                        CardUiModel(
                            cardholderNameUiModel = uiState.cardholderName,
                            cardNumberUiModel = uiState.cardNumber,
                            cardExpirationDateUiModel = uiState.cardExpirationDate,
                        ),
                    )

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
                uiState =
                    uiState.copy(
                        cardExpirationDate = newCardExpirationDate,
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
    uiState: CardRegistrationScreenUiState,
    onCardNumberChanged: (CardNumberUiModel) -> Unit,
    onCardExpirationDateChanged: (CardExpirationDateUiModel) -> Unit,
    onCardExpirationDateErrorMessageChanged: (String?) -> Unit,
    onCardholderNameChanged: (CardholderNameUiModel) -> Unit,
    onCardPasswordChanged: (CardPasswordUiModel) -> Unit,
    modifier: Modifier = Modifier,
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardRegistrationScreenPreview() {
    AndroidpaymentsTheme {
        CardRegistrationScreen(
            onBackPressed = {},
            onCardRegistered = {},
        )
    }
}
