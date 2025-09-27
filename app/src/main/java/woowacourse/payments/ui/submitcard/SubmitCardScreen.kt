package woowacourse.payments.ui.submitcard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.common.composable.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.submitcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.submitcard.textfields.CardNumberTextField
import woowacourse.payments.ui.submitcard.textfields.ExpirationDateTextField
import woowacourse.payments.ui.submitcard.textfields.PasscodeTextField

@Composable
fun SubmitCardScreen(
    stateHolder: SubmitCardStateHolder,
    onSubmitClick: (card: CardUiModel) -> Unit,
    onBackClick: () -> Unit,
) {
    val context: Context = LocalContext.current
    val focusManager: FocusManager = LocalFocusManager.current

    fun submitCard() {
        if (stateHolder.isError) {
            stateHolder.dispatchEvent(SubmitCardScreenUiEvent.ShowCardSubmitFailureMessage)
            return
        }

        when (stateHolder) {
            is SubmitCardStateHolder.AddCardStateHolder -> {
                stateHolder.dispatchEvent(SubmitCardScreenUiEvent.ShowCardAddSuccessMessage)
            }

            is SubmitCardStateHolder.EditCardStateHolder -> {
                if (!stateHolder.isChanged) {
                    stateHolder.dispatchEvent(SubmitCardScreenUiEvent.ShowCardEditFailureMessage)
                    return
                }
                stateHolder.dispatchEvent(SubmitCardScreenUiEvent.ShowCardEditSuccessMessage)
            }
        }

        onSubmitClick(stateHolder.card)
    }

    LaunchedEffect(stateHolder.uiEvent) {
        val message: String =
            when (stateHolder.uiEvent) {
                SubmitCardScreenUiEvent.ShowCardSubmitFailureMessage -> context.getString(R.string.submit_card_failure_message)
                SubmitCardScreenUiEvent.ShowCardAddSuccessMessage -> context.getString(R.string.submit_card_add_success_message)
                SubmitCardScreenUiEvent.ShowCardEditSuccessMessage -> context.getString(R.string.submit_card_edit_success_message)
                SubmitCardScreenUiEvent.ShowCardEditFailureMessage -> context.getString(R.string.submit_card_edit_failure_message)
                null -> return@LaunchedEffect
            }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        stateHolder.onEventDispatched()
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
            SubmitCardTopBar(
                stateHolder = stateHolder,
                onBackClick = onBackClick,
                onSubmitClick = { submitCard() },
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(Modifier.padding(innerPadding)) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 30.dp)
                        .clickable { stateHolder.onCardCompaniesRequested() },
                card = stateHolder.card,
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
private fun SubmitCardScreenPreview() {
    SubmitCardScreen(
        stateHolder = remember { SubmitCardStateHolder.AddCardStateHolder() },
        onSubmitClick = { _ -> },
        onBackClick = {},
    )
}
