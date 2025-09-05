package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.ExpirationDateTextField
import woowacourse.payments.ui.newcard.components.NameTextField
import woowacourse.payments.ui.newcard.components.PasswordField
import woowacourse.payments.ui.newcard.components.PaymentCardBox

@Composable
fun NewCardScreen(innerPadding: PaddingValues) {
    var cardNumber: String by rememberSaveable { mutableStateOf("") }
    var name: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var expirationDateUitState by remember { mutableStateOf(ExpirationDateUiState("")) }

    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
    ) {
        PaymentCardBox(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp),
        )
        CardNumberTextField(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 24.dp, end = 24.dp),
            value = cardNumber,
            onValueChange = { cardNumber = it },
            maxLength = 16,
        )
        ExpirationDateTextField(
            modifier =
                Modifier
                    .padding(start = 24.dp, top = 30.dp),
            value = expirationDateUitState.expirationDate.value,
            onValueChange = { expirationDateUitState.onValueChanged(it) },
            isValid = expirationDateUitState.isError,
            maxLength = 4,
        )
        NameTextField(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 30.dp, end = 24.dp),
            value = name,
            onValueChange = { name = it },
            maxLength = 30,
        )
        PasswordField(
            modifier =
                Modifier
                    .padding(start = 24.dp, top = 30.dp),
            value = password,
            onValueChange = { password = it },
            maxLength = 4,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(innerPadding = PaddingValues())
}
