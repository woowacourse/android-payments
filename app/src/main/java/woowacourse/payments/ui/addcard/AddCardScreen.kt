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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.addcard.bottomsheet.CardCompanyBottomSheet
import woowacourse.payments.ui.addcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.addcard.textfields.CardNumberTextField
import woowacourse.payments.ui.addcard.textfields.ExpirationDateTextField
import woowacourse.payments.ui.addcard.textfields.PasscodeTextField
import woowacourse.payments.ui.common.PaymentCard
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    onSaveSuccess: (card: CardUiModel) -> Unit,
    onSaveFailure: () -> Unit,
    onBackClick: () -> Unit,
) {
    val uiState = remember { AddCardScreenUiStateHolder() }

    fun saveAddedCard() {
        uiState.checkEmptyFields()
        if (uiState.isError) {
            onSaveFailure()
        } else {
            onSaveSuccess(
                CardUiModel(
                    uiState.cardNumber.value,
                    uiState.expirationDate.value,
                    uiState.cardholderName.value,
                    uiState.passcode.value,
                    uiState.cardCompany.value,
                ),
            )
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
            CardCompany.entries
                .filter { cardCompany ->
                    cardCompany != CardCompany.NONE
                }.map(CardCompany::toUiModel),
            { cardCompany: CardCompanyUiModel ->
                uiState.cardCompany.value = cardCompany
            },
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
                CardNumberTextField(uiState.cardNumber, uiState.isCardNumberError)

                ExpirationDateTextField(uiState.expirationDate, uiState.isExpirationDateError)

                CardHolderNameTextField(uiState.cardholderName)

                PasscodeTextField(uiState.passcode, uiState.isPasscodeError)
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
