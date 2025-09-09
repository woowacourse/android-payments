package woowacourse.payments.ui.newcard.component

import android.R.attr.password
import android.R.attr.top
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
        CardNumberTextField(
            number = newCardUiState.number,
            onNumberChange = onNumberChange,
            modifier = Modifier
                .fillMaxWidth()

        )
        ExpiredDateTextField(
            expiredDate = newCardUiState.expirationDate,
            onExpirationDateChange = onExpirationDateChange,
            modifier = Modifier
                .padding(top = 18.dp)
        )
        CardOwnerNameTextField(
            ownerName = newCardUiState.ownerName,
            onOwnerNameChange = onOwnerNameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
        )
        PasswordTextField(
            password = newCardUiState.password,
            onPasswordChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
        )
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
        onPasswordChange = {}
    )
}