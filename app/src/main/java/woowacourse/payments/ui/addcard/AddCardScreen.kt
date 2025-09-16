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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    val card: MutableState<CardUiModel> = remember { mutableStateOf(CardUiModel.EMPTY) }

    val isCardNumberError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isExpirationDateError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isPasscodeError: MutableState<Boolean> = remember { mutableStateOf(false) }

    val selectedCardCompany = remember { mutableStateOf(CardCompany.NONE) }

    fun isError(): Boolean = isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value

    fun checkEmptyFields() {
        if (card.value.cardNumber.isEmpty()) isCardNumberError.value = true
        if (card.value.expirationDate.isEmpty()) isExpirationDateError.value = true
        if (card.value.passcode.isEmpty()) isPasscodeError.value = true
    }

    fun saveAddedCard() {
        checkEmptyFields()
        if (isError()) {
            onSaveFailure()
        } else {
            onSaveSuccess(card.value)
            card.value = CardUiModel.EMPTY
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
                selectedCardCompany.value = cardCompany.company
            },
            onBackClick,
        )

        Column(Modifier.fillMaxSize().padding(innerPadding)) {
            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 30.dp),
                card = CardUiModel.EMPTY.copy(cardCompany = selectedCardCompany.value.toUiModel()),
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CardNumberTextField(card, isCardNumberError)

                ExpirationDateTextField(card, isExpirationDateError)

                CardHolderNameTextField(card)

                PasscodeTextField(card, isPasscodeError)
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
