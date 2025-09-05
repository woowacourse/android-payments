package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PaymentCard

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    var cardNumber: String by remember { mutableStateOf("") }
    var cardExpirationDate: String by remember { mutableStateOf("") }
    var cardHolderName: String by remember { mutableStateOf("") }
    var cardPassword: String by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 24.dp, vertical = 20.dp),
        ) {
            PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            LimitedLengthOutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                maxLength = CardNumber.CARD_NUMBER_LENGTH,
                label = { Text(stringResource(R.string.card_number)) },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(),
            )
            LimitedLengthOutlinedTextField(
                value = cardExpirationDate,
                onValueChange = { cardExpirationDate = it },
                maxLength = 4,
                label = { Text(stringResource(R.string.card_expiration_date)) },
                placeholder = { Text("MM / YY") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            LimitedLengthOutlinedTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                maxLength = CardHolderName.MAX_NAME_LENGTH,
                label = { Text(stringResource(R.string.card_holder_name)) },
                placeholder = { Text(stringResource(R.string.input_card_holder_name)) },
                supportingText = {
                    Text(
                        "${cardHolderName.length}/${CardHolderName.MAX_NAME_LENGTH}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
            LimitedLengthOutlinedTextField(
                value = cardPassword,
                onValueChange = { cardPassword = it },
                maxLength = CardPassword.CARD_PASSWORD_LENGTH,
                label = { Text(stringResource(R.string.card_password)) },
                placeholder = { Text("0000") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
