package woowacourse.payments.ui.screen.addCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onCardSaved: (CardUiModel) -> Unit,
) {
    val stateHolder =
        rememberSaveable(saver = AddCardStateHolder.saver) { AddCardStateHolder(AddCardUiState()) }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
                    stateHolder.showValidationError(!stateHolder.uiState.isFormValid)
                    if (stateHolder.uiState.isFormValid) {
                        onCardSaved(stateHolder.uiState.toCardUiModel())
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                PaymentCard(card = stateHolder.uiState.toCardUiModel())
            }

            CardNumberInputField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = stateHolder.uiState.cardNumber,
                onCardNumberChange = { stateHolder.updateCardNumber(it) },
                showValidationError = stateHolder.uiState.showValidationError,
            )

            ExpiredInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                expired = stateHolder.uiState.expired,
                onExpiredChange = { stateHolder.updateExpired(it) },
                showValidationError = stateHolder.uiState.showValidationError,
            )

            CardOwnerInputField(
                modifier = Modifier.fillMaxWidth(),
                cardOwner = stateHolder.uiState.cardOwner,
                onOwnerChange = { stateHolder.updateCardOwner(it) },
                showValidationError = stateHolder.uiState.showValidationError,
            )

            PasswordInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                password = stateHolder.uiState.password,
                onPasswordChange = { stateHolder.updatePassword(it) },
                showValidationError = stateHolder.uiState.showValidationError,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen(
            onBackPressed = {},
            onCardSaved = {},
        )
    }
}
