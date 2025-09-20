package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun AddCardContent(
    modifier: Modifier = Modifier,
    uiState: AddCardUiState,
    cardPreview: CardUiModel,
    onBackPressed: () -> Unit,
    onNumberChange: (String) -> Unit,
    onExpirationChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDismissSheet: () -> Unit,
    onSelectCompany: (CardCompanyType) -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally),
                card = cardPreview,
            )

            Spacer(Modifier.height(12.dp))

            Spacer(Modifier.height(28.dp))
            CardNumberField(
                value = uiState.number,
                onValueChange = onNumberChange,
                modifier = Modifier.fillMaxWidth(),
                error = uiState.numberError,
            )

            Spacer(Modifier.height(24.dp))
            ExpirationDateField(
                value = uiState.expiration,
                onValueChange = onExpirationChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = uiState.expirationError,
            )

            Spacer(Modifier.height(24.dp))
            UserNameField(
                value = uiState.userName,
                onValueChange = onUserNameChange,
                modifier = Modifier.fillMaxWidth(),
                error = uiState.userNameError,
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = uiState.passwordError,
            )
        }
    }

    BankSelectBottomSheet(
        visible = uiState.showCompanySheet,
        onDismissRequest = onDismissSheet,
        onSelect = onSelectCompany,
        blockUserDismiss = false,
    )
}

@Preview
@Composable
private fun AddCardContentPreview() {
    val previewHolder = AddCardStateHolder(initialShowSheet = false)

    AddCardContent(
        uiState = previewHolder.uiState,
        cardPreview = previewHolder.cardPreview,
        onBackPressed = {},
        onNumberChange = {},
        onExpirationChange = {},
        onUserNameChange = {},
        onPasswordChange = {},
        onDismissSheet = {},
        onSelectCompany = {},
        onSaveClick = {},
    )
}
