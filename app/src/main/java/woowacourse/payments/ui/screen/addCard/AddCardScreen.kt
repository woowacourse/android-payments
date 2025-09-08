package woowacourse.payments.ui.screen.addCard

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
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toPresentation

@Composable
fun AddCardScreen(
    viewModel: AddCardViewModel = remember { AddCardViewModel() },
    onBackPressed: () -> Unit,
    onCardSaved: (CardUiModel) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
                    viewModel.validateAll()
                    if (!viewModel.showValidationError) {
                        val cardUiModel =
                            Card(
                                number = viewModel.cardNumber ?: CardNumber(""),
                                expired = viewModel.expired ?: Expired(""),
                                owner = viewModel.cardOwner ?: CardOwner(""),
                                password = viewModel.password ?: Password(""),
                            ).toPresentation()
                        onCardSaved(cardUiModel)
                    }
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
                PaymentCard(
                    Card(
                        number = viewModel.cardNumber,
                        expired = viewModel.expired,
                        owner = viewModel.cardOwner,
                        password = viewModel.password,
                    ).toPresentation(),
                )
            }

            CardNumberInputField(
                cardNumber = viewModel.cardNumber,
                onCardNumberChange = viewModel::onCardNumberChange,
                modifier = Modifier.fillMaxWidth(),
                showValidationError = viewModel.showValidationError,
            )

            ExpiredInputField(
                expired = viewModel.expired,
                onExpiredChange = viewModel::onExpiredChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                showValidationError = viewModel.showValidationError,
            )

            CardOwnerInputField(
                cardOwner = viewModel.cardOwner,
                onOwnerChange = viewModel::onCardOwnerChange,
                modifier = Modifier.fillMaxWidth(),
                showValidationError = viewModel.showValidationError,
            )

            PasswordInputField(
                password = viewModel.password,
                onPasswordChange = viewModel::onPasswordChange,
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
        AddCardScreen(
            onBackPressed = {},
            onCardSaved = {},
        )
    }
}
