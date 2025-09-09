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
import woowacourse.payments.ui.components.CardNumberField
import woowacourse.payments.ui.components.ExpirationDateField
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PasswordField
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.UserNameField
import woowacourse.payments.ui.mapper.toUiModel
import woowacourse.payments.ui.strings.getErrorMessage

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
) {
    val stateHolder = remember { AddCardScreenStateHolder() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = { stateHolder.onSaveClick(onAddCard) },
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
                card = Card("", "", "", "").toUiModel(),
            )

            Spacer(Modifier.height(40.dp))
            CardNumberField(
                value = stateHolder.number,
                onValueChange = { stateHolder.onNumberChange(it) },
                modifier = Modifier.fillMaxWidth(),
                isError = stateHolder.numberErrorType != null,
                errorMessage = stateHolder.numberErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(30.dp))
            ExpirationDateField(
                value = stateHolder.expiration,
                onValueChange = { stateHolder.onExpirationChange(it) },
                modifier = Modifier.fillMaxWidth(0.5f),
                isError = stateHolder.expirationErrorType != null,
                errorMessage = stateHolder.expirationErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(30.dp))
            UserNameField(
                value = stateHolder.userName,
                onValueChange = { stateHolder.onUserNameChange(it) },
                modifier = Modifier.fillMaxWidth(),
                isError = stateHolder.userNameErrorType != null,
                errorMessage = stateHolder.userNameErrorType?.let { getErrorMessage(it) },
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = stateHolder.password,
                onValueChange = { stateHolder.onPasswordChange(it) },
                modifier = Modifier.fillMaxWidth(0.5f),
                isError = stateHolder.passwordErrorType != null,
                errorMessage = stateHolder.passwordErrorType?.let { getErrorMessage(it) },
            )
        }
    }
}

@Preview
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        onBackPressed = {},
        onAddCard = {},
    )
}
