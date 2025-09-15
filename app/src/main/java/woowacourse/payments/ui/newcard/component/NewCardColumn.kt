package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.newcard.NewCardUiState

@Composable
fun NewCardColumn(
    newCardUiState: NewCardUiState,
    numberErrorMessage: String? = null,
    expirationDateErrorMessage: String? = null,
    ownerNameErrorMessage: String? = null,
    passwordErrorMessage: String? = null,
    onNumberChange: (String) -> Unit,
    onExpirationDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        PaymentCard(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp, bottom = 28.dp)
        )
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardNumberTextField(
                number = newCardUiState.number,
                numberErrorMessage = numberErrorMessage,
                onNumberChange = onNumberChange,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
        ExpiredDateTextField(
            expiredDate = newCardUiState.expirationDate,
            expirationDateErrorMessage = expirationDateErrorMessage,
            onExpirationDateChange = onExpirationDateChange,
        )}
        Box(
            modifier = Modifier.height(86.dp)
        ) {
        CardOwnerNameTextField(
            ownerName = newCardUiState.ownerName,
            ownerNameErrorMessage = ownerNameErrorMessage,
            onOwnerNameChange = onOwnerNameChange,
            modifier = Modifier
                .fillMaxWidth()
        )}
        Box(
            modifier = Modifier.height(86.dp)
        ) {
        PasswordTextField(
            password = newCardUiState.password,
            passwordErrorMessage = passwordErrorMessage,
            onPasswordChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
        )}
    }
}

@Preview
@Composable
private fun NewCardColumnPreview() {
    var newCardUiState by remember { mutableStateOf(NewCardUiState()) }
    NewCardColumn(
        newCardUiState = newCardUiState,
        onNumberChange = {},
        onExpirationDateChange = {},
        onOwnerNameChange = {},
        onPasswordChange = {},
        numberErrorMessage = null,
        expirationDateErrorMessage = null,
    )
}