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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.component.CardExpirationDateTextField
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.CardholderNameTextField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.toBankName
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(
    onRegisteredCard: (PaymentCardUiModel) -> Unit,
    onUpdatedCard: (PaymentCardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardRegistrationScreenViewModel = rememberCardRegistrationScreenViewModel(),
) {
    val uiState = viewModel.uiState.observeAsState().value ?: return
    val uiEvent = viewModel.uiEvent.observeAsState().value
    var shouldOpenBankSelector by rememberSaveable { mutableStateOf(uiState.bankType == BankTypeUiModel.NOT_SELECTED) }

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is CardRegistrationScreenUiEvent.RegisteredCard -> onRegisteredCard(uiEvent.paymentCard)
            is CardRegistrationScreenUiEvent.UpdatedCard -> onUpdatedCard(uiEvent.paymentCard)
            null -> Unit
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardRegistrationTopAppBar(
                title =
                    when (uiState.registrationState) {
                        is CardRegistrationState.Register ->
                            stringResource(R.string.card_registration_screen_registration_top_app_bar_title)

                        is CardRegistrationState.Edit ->
                            stringResource(R.string.card_registration_screen_edit_top_app_bar_title)
                    },
                onBackClick = onBackClick,
                onSaveClick = viewModel::registerOrUpdateCard,
                isSaveButtonEnabled = uiState.canRegisterCard,
            )
        },
    ) { innerPadding ->
        CardRegistrationScreenContent(
            uiState = uiState,
            onCardClick = { shouldOpenBankSelector = true },
            onCardNumberChanged = viewModel::updateCardNumber,
            onCardExpirationDateChanged = viewModel::updateCardExpirationDate,
            onCardholderNameChanged = viewModel::updateCardholderName,
            onCardPasswordChanged = viewModel::updateCardPassword,
            modifier = Modifier.padding(innerPadding),
        )

        if (shouldOpenBankSelector) {
            BankSelectBottomSheet(
                onBankSelected = { bank ->
                    viewModel.updateBank(bank)
                    shouldOpenBankSelector = false
                },
                onDismissRequest = { shouldOpenBankSelector = false },
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
            cardholderName = uiState.cardholderName.name,
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
            onRegisteredCard = {},
            onUpdatedCard = {},
            viewModel =
                CardRegistrationScreenViewModel(
                    CardRegistrationScreenUiState(),
                ),
        )
    }
}
