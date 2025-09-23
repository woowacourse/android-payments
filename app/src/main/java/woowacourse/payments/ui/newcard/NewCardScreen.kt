package woowacourse.payments.ui.newcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.data.BankRepository
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.ui.component.CardImage
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardHolderUiModel.Companion.CARD_HOLDER_MAX_LENGTH
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardNumberUiModel.Companion.CARD_NUMBER_LENGTH
import woowacourse.payments.ui.model.ExpirationDateUiModel.Companion.EXPIRATION_DATE_LENGTH
import woowacourse.payments.ui.model.PasswordUiModel.Companion.PASSWORD_LENGTH
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.ExpirationDateTextField
import woowacourse.payments.ui.newcard.components.NameTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PasswordField
import woowacourse.payments.ui.newcard.dialog.BankBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    banks: List<Bank>,
    initialCard: PaymentCardUiModel? = null,
    newCardStateHolder: NewCardStateHolder = remember { NewCardStateHolder() },
    onBackPress: () -> Unit = {},
    onSaved: (Result<PaymentCardUiModel>) -> Unit = {},
) {
    var isShowBottomSheet by rememberSaveable { mutableStateOf(initialCard == null) }

    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackPress() },
                onSaveClick = {
                    initialCard?.let {
                        if (!newCardStateHolder.isModified(it)) return@NewCardTopBar
                    }
                    onSaved(
                        runCatching {
                            PaymentCardUiModel(
                                id = newCardStateHolder.id,
                                bankType = newCardStateHolder.bank.type,
                                cardNumber = CardNumberUiModel(newCardStateHolder.cardNumber),
                                cardHolder = CardHolderUiModel(newCardStateHolder.cardHolder),
                                expirationDate = newCardStateHolder.expirationDateUiState.expirationDate,
                            )
                        },
                    )
                },
            )
        },
    ) { innerPadding ->
        if (isShowBottomSheet) {
            BankBottomSheet(
                sheetState = modalBottomSheetState,
                banks = banks,
                onClick = {
                    newCardStateHolder.updateBank(it)
                },
                onDismiss = {
                    isShowBottomSheet = false
                },
            )
        }

        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            CardImage(
                bankType = newCardStateHolder.bank.type,
                cardNumber = newCardStateHolder.cardNumber,
                cardHolder = newCardStateHolder.cardHolder,
                expirationDate = newCardStateHolder.expirationDateUiState.expirationDate.value,
                modifier =
                    Modifier
                        .clickable {
                            isShowBottomSheet = true
                        }.align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp),
            )
            CardNumberTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 24.dp, end = 24.dp),
                value = newCardStateHolder.cardNumber,
                onValueChange = { newCardStateHolder.updateCardNumber(it) },
                maxLength = CARD_NUMBER_LENGTH,
            )
            ExpirationDateTextField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = newCardStateHolder.expirationDateUiState.expirationDate.value,
                onValueChange = { newCardStateHolder.expirationDateUiState.onValueChanged(it) },
                isValid = newCardStateHolder.expirationDateUiState.isValid,
                maxLength = EXPIRATION_DATE_LENGTH,
            )
            NameTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 30.dp, end = 24.dp),
                value = newCardStateHolder.cardHolder,
                onValueChange = { newCardStateHolder.updateCardHolder(it) },
                maxLength = CARD_HOLDER_MAX_LENGTH,
            )
            PasswordField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = newCardStateHolder.password,
                onValueChange = { newCardStateHolder.updatePassword(it) },
                maxLength = PASSWORD_LENGTH,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(
        banks = BankRepository.getBanks(),
    )
}
