package woowacourse.payments.ui.screen.registration

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.CardExpirationDateTextField
import woowacourse.payments.ui.component.CardExpirationDateUiModel
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardNumberUiModel
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.CardPasswordUiModel
import woowacourse.payments.ui.component.CardholderNameTextField
import woowacourse.payments.ui.component.CardholderNameUiModel
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(viewModel: CardRegistrationScreenViewModel = rememberCardRegistrationScreenViewModel()) {
    val focusManager = LocalFocusManager.current
    val snackbarState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val uiState = viewModel.uiState
    val uiEvent = viewModel.uiEvent

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            null -> Unit
            is CardRegistrationScreenUiEvent.ShowSnackbar -> {
                snackbarState.showSnackbar(uiEvent.message.asString(context))
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = { },
                onSaveClick = {
                    focusManager.clearFocus()
                    viewModel.registerCard()
                },
                isSaveButtonEnabled = uiState.isSaveButtonEnabled,
            )
        },
    ) { innerPadding ->
        CardRegistrationScreenContent(
            uiState = uiState,
            onCardNumberChanged = viewModel::updateCardNumber,
            onCardExpirationDateChanged = viewModel::updateCardExpirationDate,
            onCardholderNameChanged = viewModel::updateCardholderName,
            onCardPasswordChanged = viewModel::updateCardPassword,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun CardRegistrationScreenContent(
    uiState: CardRegistrationScreenUiState,
    onCardNumberChanged: (CardNumberUiModel) -> Unit,
    onCardExpirationDateChanged: (CardExpirationDateUiModel) -> Unit,
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
            cardNumber = uiState.cardNumber,
            onCardNumberChanged = onCardNumberChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        CardExpirationDateTextField(
            cardExpirationDate = uiState.cardExpirationDate,
            onCardExpirationDateChanged = onCardExpirationDateChanged,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardholderNameTextField(
            cardholderName = uiState.cardholderName,
            onCardholderNameChanged = onCardholderNameChanged,
            modifier = Modifier.fillMaxWidth(),
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
        CardRegistrationScreen()
    }
}
