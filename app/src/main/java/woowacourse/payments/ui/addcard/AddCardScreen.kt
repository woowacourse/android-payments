package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import woowacourse.payments.ui.addcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.addcard.textfields.CardNumberTextField
import woowacourse.payments.ui.addcard.textfields.ExpirationDateTextField
import woowacourse.payments.ui.addcard.textfields.PasscodeTextField
import woowacourse.payments.ui.common.composable.PaymentCard
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun AddCardScreen(
    stateHolder: AddCardScreenUiStateHolder,
    onSaveSuccess: (card: CardUiModel) -> Unit,
    onSaveFailure: () -> Unit,
    onBackClick: () -> Unit,
) {
    val focusManager: FocusManager = LocalFocusManager.current

    fun saveAddedCard() {
        if (stateHolder.isError) {
            onSaveFailure()
            return
        }
        onSaveSuccess(stateHolder.card)
    }

    LaunchedEffect(stateHolder.shouldMoveFocus) {
        if (stateHolder.shouldMoveFocus) {
            focusManager.moveFocus(FocusDirection.Next)
            stateHolder.onFocusMoved()
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
        Column(Modifier.padding(innerPadding)) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 30.dp),
                card = CardUiModel.EMPTY.copy(cardCompany = stateHolder.cardCompany),
            )

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CardNumberTextField(
                    stateHolder.cardNumber,
                    stateHolder.isCardNumberError,
                    stateHolder::onCardNumberChanged,
                )

                ExpirationDateTextField(
                    stateHolder.expirationDate,
                    stateHolder.isExpirationDateError,
                    stateHolder::onExpirationDateChanged,
                )

                CardHolderNameTextField(
                    stateHolder.cardholderName,
                    stateHolder::onCardholderNameChanged,
                )

                PasscodeTextField(
                    stateHolder.passcode,
                    stateHolder.isPasscodeError,
                    stateHolder::onPasscodeChanged,
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "카드 추가 화면")
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        stateHolder = remember { AddCardScreenUiStateHolder() },
        onSaveSuccess = { _ -> },
        onSaveFailure = {},
        onBackClick = {},
    )
}
