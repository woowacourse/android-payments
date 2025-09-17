@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui.screen.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.registration.CardCompanySelectBottomSheet
import woowacourse.payments.ui.component.registration.CardExpirationDateTextField
import woowacourse.payments.ui.component.registration.CardNumberTextField
import woowacourse.payments.ui.component.registration.CardPasswordTextField
import woowacourse.payments.ui.component.registration.CardRegistrationTopAppBar
import woowacourse.payments.ui.component.registration.CardholderNameTextField
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegistrationScreen(
    onBackPressed: () -> Unit,
    onCardRegistered: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var uiState by rememberSaveable { mutableStateOf(CardRegistrationScreenUiState()) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(uiState.isBottomSheetOpen) {
        if (uiState.isBottomSheetOpen) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    Scaffold(
        topBar = {
            CardRegistrationTopAppBar(
                onBackClick = {
                    onBackPressed()
                },
                onSaveClick = {
                    onCardRegistered(
                        CardUiModel(
                            cardCompanyUiModel = uiState.cardCompany,
                            cardholderNameUiModel = uiState.cardholderName,
                            cardNumberUiModel = uiState.cardNumber,
                            cardExpirationDateUiModel = uiState.cardExpirationDate,
                        ),
                    )
                },
                isSaveButtonEnabled = uiState.isRegistrableCard,
            )
        },
    ) { innerPadding ->
        CardRegistrationScreenContent(
            uiState = uiState,
            sheetState = sheetState,
            onCardClick = {
                uiState = uiState.copy(isBottomSheetOpen = true)
            },
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
            onCardCompanySelected = { newCardCompany ->
                uiState = uiState.copy(isBottomSheetOpen = false, cardCompany = newCardCompany)
            },
            onBottomSheetDismissRequest = {
                uiState = uiState.copy(isBottomSheetOpen = false)
            },
            modifier = modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun CardRegistrationScreenContent(
    uiState: CardRegistrationScreenUiState,
    sheetState: SheetState,
    onCardClick: () -> Unit,
    onCardNumberChanged: (CardNumberUiModel) -> Unit,
    onCardExpirationDateChanged: (CardExpirationDateUiModel) -> Unit,
    onCardExpirationDateErrorMessageChanged: (String?) -> Unit,
    onCardholderNameChanged: (CardholderNameUiModel) -> Unit,
    onCardPasswordChanged: (CardPasswordUiModel) -> Unit,
    onCardCompanySelected: (CardCompanyUiModel) -> Unit,
    onBottomSheetDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.isBottomSheetOpen) {
        CardCompanySelectBottomSheet(
            onCardCompanyClick = {
                onCardCompanySelected(it)
            },
            onDismissRequest = {
                onBottomSheetDismissRequest()
            },
            sheetState = sheetState,
        )
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        PaymentCard(
            onCardClick = onCardClick,
            card = uiState.toCardUiModel(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

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
private fun CardRegistrationScreenPreview() {
    AndroidpaymentsTheme {
        CardRegistrationScreen(
            onBackPressed = {},
            onCardRegistered = {},
        )
    }
}
