package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.addcard.bottomsheet.CardCompanyBottomSheet
import woowacourse.payments.ui.addcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.addcard.textfields.CardNumberTextField
import woowacourse.payments.ui.addcard.textfields.ExpirationDateTextField
import woowacourse.payments.ui.addcard.textfields.PasscodeTextField
import woowacourse.payments.ui.common.composable.PaymentCard
import woowacourse.payments.ui.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    onSaveSuccess: (card: CardUiModel) -> Unit,
    onSaveFailure: () -> Unit,
    onBackClick: () -> Unit,
) {
    val uiState = remember { AddCardScreenUiStateHolder() }
    val focusManager: FocusManager = LocalFocusManager.current

    fun saveAddedCard() {
        uiState.validate()
        if (uiState.isError) {
            onSaveFailure()
            return
        }
        onSaveSuccess(uiState.card)
    }

    LaunchedEffect(uiState.shouldMoveFocus.value) {
        if (uiState.shouldMoveFocus.value) {
            focusManager.moveFocus(FocusDirection.Next)
            uiState.onFocusMoved()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AddCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = { saveAddedCard() },
            )
        },
    ) { innerPadding: PaddingValues ->
        CardCompanyBottomSheet(
            uiState.cardCompany,
            AddCardScreenUiStateHolder.CARD_COMPANIES,
            onBackClick,
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 30.dp),
                card = CardUiModel.EMPTY.copy(cardCompany = uiState.cardCompany.value),
            )

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CardNumberTextField(
                    uiState.cardNumber,
                    uiState.isCardNumberError,
                    { newValue: String -> uiState.updateCardNumber(newValue) },
                )

                ExpirationDateTextField(
                    uiState.expirationDate,
                    uiState.isExpirationDateError,
                    { newValue: String -> uiState.updateExpirationDate(newValue) },
                )

                CardHolderNameTextField(
                    uiState.cardholderName,
                    { newValue: String -> uiState.updateCardholderName(newValue) },
                )

                PasscodeTextField(
                    uiState.passcode,
                    uiState.isPasscodeError,
                    { newValue: String -> uiState.updatePasscode(newValue) },
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "카드 추가 화면")
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        onSaveSuccess = { _ -> },
        onSaveFailure = {},
        onBackClick = {},
    )
}
