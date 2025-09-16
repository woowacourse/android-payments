package woowacourse.payments.ui.screen.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.component.CardExpirationDateTextField
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.CardholderNameTextField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.toBankName
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(
    onRegistrationComplete: (PaymentCardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardRegistrationScreenViewModel = rememberCardRegistrationScreenViewModel(),
) {
    val uiState by viewModel.uiState.observeAsState(CardRegistrationScreenUiState())
    val uiEvent by viewModel.uiEvent.observeAsState()

    LaunchedEffect(uiEvent) {
        when (val event = uiEvent) {
            is CardRegistrationScreenUiEvent.RegisteredCard ->
                event.paymentCard.let(onRegistrationComplete)

            null -> Unit
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = onBackClick,
                onSaveClick = viewModel::registerCard,
                isSaveButtonEnabled = uiState.isSaveButtonEnabled,
            )
        },
    ) { innerPadding ->
        CardRegistrationScreenContent(
            uiState = uiState,
            onCardClick = viewModel::openBankSelectorBottomSheet,
            onCardNumberChanged = viewModel::updateCardNumber,
            onCardExpirationDateChanged = viewModel::updateCardExpirationDate,
            onCardholderNameChanged = viewModel::updateCardholderName,
            onCardPasswordChanged = viewModel::updateCardPassword,
            modifier = Modifier.padding(innerPadding),
        )

        if (uiState.openBankSelectorBottomSheet) {
            BankSelectBottomSheet(
                onBankSelected = viewModel::updateBank,
                onDismiss = viewModel::closeBankSelectorBottomSheet,
            )
        }
    }
}

@Composable
private fun CardRegistrationScreenContent(
    uiState: CardRegistrationScreenUiState,
    onCardClick: () -> Unit,
    onCardNumberChanged: (String) -> Unit,
    onCardExpirationDateChanged: (String) -> Unit,
    onCardholderNameChanged: (String) -> Unit,
    onCardPasswordChanged: (String) -> Unit,
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

        val paymentCard = uiState.toPaymentCardUiModel()
        PaymentCard(
            bankName = paymentCard.bankType.toBankName(),
            number = paymentCard.displayCardNumber(),
            expirationDate = paymentCard.displayExpirationDate(),
            cardholderName = paymentCard.upperCardholderName,
            backgroundColor = paymentCard.bankType.bgColor,
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onCardClick() },
        )

        Spacer(modifier = Modifier.height(40.dp))

        CardNumberTextField(
            cardNumber = uiState.cardNumber.number,
            onCardNumberChanged = onCardNumberChanged,
            errorMessage = uiState.cardNumberErrorMessageResId?.let { stringResource(it) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        CardExpirationDateTextField(
            cardExpirationDate = uiState.cardExpirationDate.expirationDate,
            onCardExpirationDateChanged = onCardExpirationDateChanged,
            errorMessage = uiState.cardExpirationDateErrorMessageResId?.let { stringResource(it) },
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardholderNameTextField(
            cardholderName = uiState.cardholderName.displayedName,
            onCardholderNameChanged = onCardholderNameChanged,
            maxLength = uiState.cardholderName.maxLength,
            errorMessage = uiState.cardholderNameErrorMessageResId?.let { stringResource(it) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardPasswordTextField(
            cardPassword = uiState.cardPassword.password,
            onCardPasswordChanged = onCardPasswordChanged,
            errorMessage = uiState.cardPasswordErrorMessageResId?.let { stringResource(it) },
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardRegistrationScreenPreview() {
    AndroidpaymentsTheme {
        CardRegistrationScreen(
            onBackClick = {},
            onRegistrationComplete = {},
        )
    }
}
