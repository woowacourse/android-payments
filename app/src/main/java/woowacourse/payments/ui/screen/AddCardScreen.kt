package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.domain.model.Card
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
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
) {
    val stateHolder = remember { AddCardScreenStateHolder() }

    var showSheet by rememberSaveable { mutableStateOf(true) }
    var selectedBankType by rememberSaveable { mutableStateOf(BankType.NOT_SELECTED) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
                    if (selectedBankType == BankType.NOT_SELECTED) {
                        showSheet = true
                        return@NewCardTopBar
                    }
                    stateHolder.onSaveClick(onAddCard, selectedBankType)
                },
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
                card = CardUiModel.EMPTY.copy(bankType = selectedBankType),
            )

            Spacer(Modifier.height(40.dp))
            CardNumberField(
                value = stateHolder.state.number,
                onValueChange = stateHolder::onNumberChange,
                modifier = Modifier.fillMaxWidth(),
                error = stateHolder.state.numberErrorType,
            )

            Spacer(Modifier.height(30.dp))
            ExpirationDateField(
                value = stateHolder.state.expiration,
                onValueChange = stateHolder::onExpirationChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = stateHolder.state.expirationErrorType,
            )

            Spacer(Modifier.height(30.dp))
            UserNameField(
                value = stateHolder.state.userName,
                onValueChange = stateHolder::onUserNameChange,
                modifier = Modifier.fillMaxWidth(),
                error = stateHolder.state.userNameErrorType,
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = stateHolder.state.password,
                onValueChange = stateHolder::onPasswordChange,
                modifier = Modifier.fillMaxWidth(0.5f),
                error = stateHolder.state.passwordErrorType,
            )
        }
    }

    BankSelectBottomSheet(
        visible = showSheet,
        onDismissRequest = { showSheet = false },
        onSelect = { bank ->
            selectedBankType = bank
            showSheet = false
        },
        blockUserDismiss = false,
    )
}

@Preview
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        onBackPressed = {},
        onAddCard = {},
    )
}
