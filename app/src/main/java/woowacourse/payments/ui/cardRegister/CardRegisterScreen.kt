package woowacourse.payments.ui.cardRegister

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import woowacourse.payments.ui.cardRegister.components.CardRegisterTopBar
import woowacourse.payments.ui.cardRegister.components.PaymentCard
import woowacourse.payments.ui.cardRegister.components.PaymentTextField
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.common.DateVisualTransformation
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardRegisterScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var expiredDate by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CardRegisterTopBar(
                onBackClick = { /* 뒤로가기 */ },
                onSaveClick = { /* 저장하기 */ },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .padding(top = 14.dp)
                        .align(Alignment.CenterHorizontally),
            )
            PaymentTextField(
                text = cardNumber,
                onValueChanged = { cardNumber = it },
                label = stringResource(R.string.card_number_label),
                placeholder = stringResource(R.string.card_number_place_holder),
                maxLength = 16,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = CreditCardVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
            )
            PaymentTextField(
                text = expiredDate,
                onValueChanged = { expiredDate = it },
                label = stringResource(R.string.expired_date_label),
                placeholder = stringResource(R.string.expired_date_place_holder),
                maxLength = 4,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = DateVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 30.dp),
            )
            PaymentTextField(
                text = ownerName,
                onValueChanged = { ownerName = it },
                label = stringResource(R.string.card_owner_label),
                placeholder = stringResource(R.string.card_owner_place_holder),
                supportingText = {
                    Text(
                        text =
                            stringResource(
                                R.string.card_owner_supporting_text,
                                ownerName.length,
                            ),
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                maxLength = 30,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
            )
            PaymentTextField(
                text = password,
                onValueChanged = { password = it },
                label = stringResource(R.string.card_password_label),
                placeholder = stringResource(R.string.card_password_place_holder),
                maxLength = 4,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PasswordVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 10.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardRegisterScreenPreview() {
    AndroidpaymentsTheme {
        CardRegisterScreen()
    }
}
