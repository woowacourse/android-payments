package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.components.BankSelectBottomSheet
import woowacourse.payments.ui.components.CardNumberField
import woowacourse.payments.ui.components.ExpirationDateField
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PasswordField
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.UserNameField
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun AddCardScreen(
    uiState: AddCardScreenUiState,
    cardForPreview: CardUiModel,
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
    onNumberChange: (String) -> Unit,
    onExpirationChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDismissSheet: () -> Unit,
    onSelectCardCompany: (CardCompanyType) -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = onSaveClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(Modifier.height(14.dp))
            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                card = cardForPreview,
            )

            Spacer(Modifier.height(40.dp))
            CardNumberField(
                value = uiState.formState.number,
                onValueChange = onNumberChange,
                modifier = Modifier.fillMaxWidth(),
                error = uiState.formState.numberErrorType,
            )

            Spacer(Modifier.height(30.dp))
            ExpirationDateField(
                value = uiState.formState.expiration,
                onValueChange = onExpirationChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = uiState.formState.expirationErrorType,
            )

            Spacer(Modifier.height(30.dp))
            UserNameField(
                value = uiState.formState.userName,
                onValueChange = onUserNameChange,
                modifier = Modifier.fillMaxWidth(),
                error = uiState.formState.userNameErrorType,
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = uiState.formState.password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = uiState.formState.passwordErrorType,
            )
        }
    }

    BankSelectBottomSheet(
        visible = uiState.showSheet,
        onDismissRequest = onDismissSheet,
        onSelect = onSelectCardCompany,
        blockUserDismiss = false,
    )
}

@Preview
@Composable
private fun AddCardScreenPreview() {
    val dummyStateHolder = remember { AddCardScreenStateHolder(initialShowSheet = false) }

    AddCardScreen(
        uiState = dummyStateHolder.uiState,
        cardForPreview = dummyStateHolder.cardForPreview,
        onBackPressed = {},
        onAddCard = {},
        onNumberChange = {},
        onExpirationChange = {},
        onUserNameChange = {},
        onPasswordChange = {},
        onDismissSheet = {},
        onSelectCardCompany = {},
        onSaveClick = {},
    )
}
