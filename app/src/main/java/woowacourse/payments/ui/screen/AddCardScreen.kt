package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(viewModel: AddCardViewModel = remember { AddCardViewModel() }) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = {},
                onSaveClick = {
                    viewModel.validateAll()
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                PaymentCard()
            }

            CardNumberInputField(
                cardNumber = viewModel.cardNumber,
                onCardNumberChange = { viewModel.onCardNumberChange(it) },
                modifier = Modifier.fillMaxWidth(),
                showValidationError = viewModel.showValidationError,
            )

            ExpiredInputField(
                expired = viewModel.expired,
                onExpiredChange = { viewModel.onExpiredChange(it) },
                modifier = Modifier.fillMaxWidth(0.5f),
                showValidationError = viewModel.showValidationError,
            )

            CardOwnerInputField(
                cardOwner = viewModel.cardOwner,
                onOwnerChange = { viewModel.onCardOwnerChange(it) },
                modifier = Modifier.fillMaxWidth(),
                showValidationError = viewModel.showValidationError,
            )

            PasswordInputField(
                password = viewModel.password,
                onPasswordChange = { viewModel.onPasswordChange(it) },
                modifier = Modifier.fillMaxWidth(0.5f),
                showValidationError = viewModel.showValidationError,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen()
    }
}
